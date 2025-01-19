package ru.hofftech.consolepackages.datastorage.repository;

import ru.hofftech.consolepackages.datastorage.model.entity.BillingOrder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Repository to work with billing orders.
 * <p>
 * This repository provides methods to create a billing order and to receive billing orders for a user by period.
 * </p>
 */
public interface BillingOrderRepository {

    /**
     * Creates a new billing order.
     *
     * @param clientId   the ID of the client
     * @param orderDate  the date of the order
     * @param amount     the total amount for the order
     * @param packageQty the quantity of packages in the order
     * @param truckId    the id of truck used for the order
     * @param comment    additional comments or notes about the order
     */
    String create(
            String clientId,
            Date orderDate,
            BigDecimal amount,
            Integer packageQty,
            UUID truckId,
            String comment);

    /**
     * Retrieves billing orders for a client by period.
     *
     * @param clientId  the ID of the client
     * @param startDate the start date of the period
     * @param endDate   the end date of the period
     * @return list of billing orders
     */
    List<BillingOrder> receiveForUserByPeriod(String clientId, Date startDate, Date endDate);
}
