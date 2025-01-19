package ru.hofftech.consolepackages.service.billing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import ru.hofftech.consolepackages.datastorage.repository.BillingOrderRepository;
import ru.hofftech.consolepackages.model.Truck;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Date;
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
    public void creatLoadPackageBill(List<Truck> trucks, String clientId) {
        createBillsByTracks(trucks, loadPrice, clientId);
    }

    @Override
    public void creatUnloadPackageBill(List<Truck> trucks, String clientId) {
        createBillsByTracks(trucks, unloadPrice, clientId);
    }

    private void createBillsByTracks(List<Truck> trucks, Integer price, String clientId) {
        for (Truck truck : trucks) {
            BigDecimal totalTruckPrice = new BigDecimal(0);
            for (ru.hofftech.consolepackages.model.Package curPackage : truck.getPackages()) {
                totalTruckPrice = calcPrice(price, curPackage, totalTruckPrice);
            }

            var orderId = billingOrderRepository.create(
                    clientId,
                    Date.from(Instant.now(Clock.system(ZoneOffset.UTC))),
                    totalTruckPrice,
                    truck.calcPackagesCount(),
                    truck.getId(),
                    String.format("Bill for loading packages on truck %s", truck.getId())
            );

            log.info("Bill order {} has created", orderId);
        }
    }

    @NotNull
    private static BigDecimal calcPrice(Integer price, ru.hofftech.consolepackages.model.Package curPackage, BigDecimal currentPrice) {
        return currentPrice.add(BigDecimal.valueOf((long) curPackage.calcVolume() * price));
    }
}
