package ru.hofftech.consolepackages.service.billing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import ru.hofftech.consolepackages.datastorage.model.entity.BillingOrder;
import ru.hofftech.consolepackages.datastorage.model.entity.OperationType;
import ru.hofftech.consolepackages.datastorage.repository.BillingOrderRepository;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.model.dto.billing.BillingResponse;
import ru.hofftech.consolepackages.model.dto.billing.BillOrderGroup;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
public class PackageBillingServiceImpl implements PackageBillingService {

    private final BillingOrderRepository billingOrderRepository;
    private final Clock clock;

    @Value("${spring.bill.load-price}")
    private Integer loadPrice;

    @Value("${spring.bill.unload-price}")
    private Integer unloadPrice;

    @Override
    public void creatPackageBill(List<Truck> trucks, String clientId, OperationType operationType) {
        createBillsByTracks(trucks, operationType == OperationType.LOAD ? loadPrice : unloadPrice, clientId, operationType);
    }

    @Override
    public List<BillingResponse> returnBillingSummaryByClient(String clientId, LocalDate fromDate, LocalDate toDate) {
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

    private void createBillsByTracks(List<Truck> trucks, Integer price, String clientId, OperationType operationType) {
        for (Truck truck : trucks) {

            if (truck.getPackages().isEmpty()) {
                continue;
            }

            var totalTruckPrice = BigDecimal.ZERO;
            for (ru.hofftech.consolepackages.model.Package curPackage : truck.getPackages()) {
                totalTruckPrice = calcPrice(price, curPackage, totalTruckPrice);
            }

            var billingOrder = billingOrderRepository.save(
                    BillingOrder.builder()
                            .clientId(clientId)
                            .orderDate(LocalDate.ofInstant(clock.instant(), ZoneId.of("UTC")))
                            .amount(totalTruckPrice)
                            .packageQty(truck.calcPackagesCount())
                            .truckId(truck.getId())
                            .comment(String.format("Bill for loading packages on truck %s", truck.getId()))
                            .operationType(operationType)
                            .build()
            );

            log.info("Bill order {} has created", billingOrder.getId());
        }
    }

    @NotNull
    private static BigDecimal calcPrice(Integer price, ru.hofftech.consolepackages.model.Package curPackage, BigDecimal currentPrice) {
        return currentPrice.add(BigDecimal.valueOf((long) curPackage.calcVolume() * price));
    }
}
