package ru.hofftech.consolepackages.datastorage.repository.impl;

import ru.hofftech.consolepackages.datastorage.model.entity.BillingOrder;
import ru.hofftech.consolepackages.datastorage.repository.BillingOrderRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link BillingOrderRepository} that stores billing orders in memory.
 * <p>
 * This repository provides methods to create a billing order and to receive billing orders for a user by period.
 * </p>
 */
public class InMemoryBillingOrderRepository implements BillingOrderRepository {
    private final Map<String, BillingOrder> billingOrders = new HashMap<>();

    @Override
    public BillingOrder save(BillingOrder billingOrder) {
        billingOrders.put(billingOrder.getId(), billingOrder);

        return billingOrder;
    }

    @Override
    public List<BillingOrder> receiveForUserByPeriod(String clientId, LocalDate startDate, LocalDate endDate) {
        return billingOrders.values().stream()
                .filter(b -> b.getClientId().equals(clientId)
                        && b.getOrderDate().isAfter(startDate)
                        && b.getOrderDate().isBefore(endDate))
                .toList();
    }
}
