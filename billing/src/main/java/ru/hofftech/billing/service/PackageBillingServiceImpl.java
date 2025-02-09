package ru.hofftech.billing.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import ru.hofftech.billing.datastorage.BillingOrderRepository;
import ru.hofftech.billing.model.dto.BillingResponse;
import ru.hofftech.billing.model.dto.CreatePackageBillRequest;
import ru.hofftech.billing.model.dto.GenerateReportByPeriodResponse;
import ru.hofftech.billing.model.dto.PackageBillDto;
import ru.hofftech.billing.model.entity.BillOrderGroup;
import ru.hofftech.billing.model.entity.BillingOrder;
import ru.hofftech.billing.model.entity.OperationType;
import ru.hofftech.billing.utils.DateUtils;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Implementation of {@link PackageBillingService} that provides methods to create a billing order and to receive billing orders for a user by period.
 * <p>
 * This implementation uses a repository to create a billing order and to retrieve billing orders for a user by period.
 * The billing order is created by grouping the billing orders by date and operation type.
 * </p>
 */
@Slf4j
@RequiredArgsConstructor
public class PackageBillingServiceImpl implements PackageBillingService {

    private final BillingOrderRepository billingOrderRepository;
    private final Clock clock;

    @Value("${spring.bill.load-price}")
    private Integer loadPrice;

    @Value("${spring.bill.unload-price}")
    private Integer unloadPrice;

    /**
     * Creates a billing order based on a list of packages.
     *
     * @param createPackageBillRequest the request object containing the list of packages to bill
     */
    @Override
    public void creatPackageBill(CreatePackageBillRequest createPackageBillRequest) {
        createBillsByTracks(
                createPackageBillRequest.packageBillDtos(),
                createPackageBillRequest.operationType() == OperationType.LOAD ? loadPrice : unloadPrice,
                createPackageBillRequest.clientId(),
                createPackageBillRequest.operationType());
    }

    /**
     * Generates a report of package bills for a user within a specified period.
     *
     * @param userId   the user id
     * @param fromDate the start date of the period
     * @param toDate   the end date of the period
     * @return a response containing the report
     */
    @Override
    @Cacheable(value = "billing-cache", key = "#userId + '_' + #fromDate + '_' + #toDate")
    public GenerateReportByPeriodResponse generateReportByPeriod(
            @NotNull
            String userId,
            @NotNull
            LocalDate fromDate,
            @NotNull
            LocalDate toDate) {
        var orders = returnBillingSummaryByClient(userId, fromDate, toDate);

        if (orders.isEmpty()) {
            return new GenerateReportByPeriodResponse();
        }

        var result = new GenerateReportByPeriodResponse();

        for (var order : orders) {
            result.addReportString(formatToreportString(order));
        }

        return result;
    }

    private List<BillingResponse> returnBillingSummaryByClient(String clientId, LocalDate fromDate, LocalDate toDate) {
        List<BillingOrder> orders = billingOrderRepository.readBillingOrdersByClientIdAndOrderDateBetween(clientId, fromDate, toDate);

        if (orders.isEmpty()) {
            return new ArrayList<>(0);
        }

        var result = new ArrayList<BillingResponse>();

        var groupedOrders = orders
                .stream()
                .collect(groupingBy(
                        billingOrder -> new BillOrderGroup(billingOrder.getOrderDate(), billingOrder.getOperationType()),
                        toList()));

        for (var entry : groupedOrders.entrySet()) {
            result.add(generateByOrderResponse(entry.getKey(), entry.getValue()));
        }

        return result;
    }

    private String formatToreportString(BillingResponse orderSummary) {
        return String.format(
                "%s; %s; %s машин; %s посылок; %s рублей",
                orderSummary.date().format(DateTimeFormatter.ofPattern(DateUtils.DATE_FORMAT)),
                OperationType.returnLabel(orderSummary.operationType()),
                orderSummary.truckCount(),
                orderSummary.packageCount(),
                orderSummary.amount());
    }

    private BillingResponse generateByOrderResponse(BillOrderGroup orderGroup, List<BillingOrder> orders) {

        var summary = orders.stream()
                .map(BillingOrder::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var packageQtySum = orders.stream()
                .mapToInt(BillingOrder::getPackageQty)
                .sum();

        var truckIdCount = orders.stream()
                .map(BillingOrder::getTruckId)
                .distinct()
                .count();

        return BillingResponse.builder()
                .date(orderGroup.orderDate())
                .operationType(orderGroup.operationType())
                .truckCount(truckIdCount)
                .packageCount(packageQtySum)
                .amount(summary)
                .build();
    }

    private void createBillsByTracks(List<PackageBillDto> bills, Integer price, String clientId, OperationType operationType) {
        for (var bill : bills) {

            if (bill.packageVolumes().isEmpty()) {
                continue;
            }

            var totalTruckPrice = BigDecimal.ZERO;
            for (var packageVolume : bill.packageVolumes()) {
                totalTruckPrice = calcPrice(price, packageVolume, totalTruckPrice);
            }

            var billingOrder = billingOrderRepository.save(
                    BillingOrder.builder()
                            .clientId(clientId)
                            .orderDate(LocalDate.ofInstant(clock.instant(), ZoneId.of("UTC")))
                            .amount(totalTruckPrice)
                            .packageQty(bill.packageVolumes().size())
                            .truckId(bill.truckId())
                            .comment(String.format("Bill for loading packages on truck %s", bill.truckId()))
                            .operationType(operationType)
                            .build()
            );

            log.info("Bill order {} has created", billingOrder.getId());
        }
    }

    @NotNull
    private static BigDecimal calcPrice(Integer price, Double packageVolume, BigDecimal currentPrice) {
        return currentPrice.add(BigDecimal.valueOf(packageVolume * price));
    }
}
