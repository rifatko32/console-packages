package ru.hofftech.consolepackages.service.billing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import ru.hofftech.consolepackages.datastorage.model.entity.BillingOrder;
import ru.hofftech.consolepackages.datastorage.model.entity.OperationType;
import ru.hofftech.consolepackages.datastorage.repository.BillingOrderRepository;
import ru.hofftech.consolepackages.model.Truck;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

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

    private void createBillsByTracks(List<Truck> trucks, Integer price, String clientId, OperationType operationType) {
        for (Truck truck : trucks) {
            BigDecimal totalTruckPrice = new BigDecimal(0);
            for (ru.hofftech.consolepackages.model.Package curPackage : truck.getPackages()) {
                totalTruckPrice = calcPrice(price, curPackage, totalTruckPrice);
            }

            var billingOrder = billingOrderRepository.save(
                    BillingOrder.builder()
                            .clientId(clientId)
                            .orderDate(LocalDate.from(Instant.now(Clock.system(ZoneOffset.UTC))))
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
