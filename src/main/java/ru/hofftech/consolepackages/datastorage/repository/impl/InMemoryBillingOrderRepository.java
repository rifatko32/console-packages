package ru.hofftech.consolepackages.datastorage.repository.impl;

import ru.hofftech.consolepackages.datastorage.model.entity.BillingOrder;
import ru.hofftech.consolepackages.datastorage.model.entity.OperationType;
import ru.hofftech.consolepackages.datastorage.repository.BillingOrderRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Implementation of {@link BillingOrderRepository} that stores billing orders in memory.
 * <p>
 * This repository provides methods to create a billing order and to receive billing orders for a user by period.
 * </p>
 */
public class InMemoryBillingOrderRepository implements BillingOrderRepository {
    private final Map<String, BillingOrder> billingOrders = new HashMap<>();

    @Override
    public String create(
            String clientId,
            Date orderDate,
            BigDecimal amount,
            Integer packageQty,
            UUID truckId,
            String comment,
            OperationType operationType) {
        var orderId = UUID.randomUUID().toString();
        billingOrders.put(orderId, new BillingOrder(clientId, orderDate, amount, packageQty, truckId, comment, operationType));

        return orderId;
    }

    @Override
    public List<BillingOrder> receiveForUserByPeriod(String clientId, Date startDate, Date endDate) {
        return billingOrders.values().stream()
                .filter(b -> b.getClientId().equals(clientId)
                        && b.getOrderDate().after(startDate)
                        && b.getOrderDate().before(endDate))
                .toList();
    }
}
