package ru.hofftech.consolepackages.service.billing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import ru.hofftech.consolepackages.datastorage.model.entity.BillingOrder;
import ru.hofftech.consolepackages.datastorage.model.entity.OperationType;
import ru.hofftech.consolepackages.datastorage.repository.BillingOrderRepository;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.model.dto.billing.BillingByUserSummaryResponse;
import ru.hofftech.consolepackages.model.dto.billing.BillOrderGroup;

import java.math.BigDecimal;
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

    @Value("${spring.bill.load-price}")
    private Integer loadPrice;

    @Value("${spring.bill.unload-price}")
    private Integer unloadPrice;

    @Override
    public void creatPackageBill(List<Truck> trucks, String clientId, OperationType operationType) {
        createBillsByTracks(trucks, operationType == OperationType.LOAD ? loadPrice : unloadPrice, clientId, operationType);
    }

    @Override
    public List<BillingByUserSummaryResponse> returnBillingSummaryByClient(String clientId, LocalDate fromDate, LocalDate toDate) {
        List<BillingOrder> orders = billingOrderRepository.receiveForUserByPeriod(clientId, fromDate, toDate);

        if (orders.isEmpty()) {
            return new ArrayList<>(0);
        }

        var result = new ArrayList<BillingByUserSummaryResponse>();

        var groupedOrders = orders
                .stream()
                .collect(groupingBy(
                        bo -> new BillOrderGroup(bo.getOrderDate(), bo.getOperationType()),
                        toList()));

        for (var entry : groupedOrders.entrySet()) {
            result.add(generateByOrderResponse(entry.getKey(), entry.getValue()));
        }

        return result;
    }

    private BillingByUserSummaryResponse generateByOrderResponse(BillOrderGroup orderGroup, List<BillingOrder> orders) {

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

        return new BillingByUserSummaryResponse(
                orderGroup.orderDate(),
                orderGroup.operationType(),
                truckIdCount,
                packageQtySum,
                summary
        );
    }

    private void createBillsByTracks(List<Truck> trucks, Integer price, String clientId, OperationType operationType) {
        for (Truck truck : trucks) {

            if (truck.getPackages().isEmpty()) {
                continue;
            }

            BigDecimal totalTruckPrice = new BigDecimal(0);
            for (ru.hofftech.consolepackages.model.Package curPackage : truck.getPackages()) {
                totalTruckPrice = calcPrice(price, curPackage, totalTruckPrice);
            }

            var billingOrder = billingOrderRepository.save(
                    BillingOrder.builder()
                            .clientId(clientId)
                            .orderDate(LocalDate.now(ZoneId.of("UTC")))
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
