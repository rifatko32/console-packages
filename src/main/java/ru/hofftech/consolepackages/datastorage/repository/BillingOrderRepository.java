package ru.hofftech.consolepackages.datastorage.repository;

import ru.hofftech.consolepackages.datastorage.model.entity.BillingOrder;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository to work with billing orders.
 * <p>
 * This repository provides methods to create a billing order and to receive billing orders for a user by period.
 * </p>
 */
public interface BillingOrderRepository {

/**
 * Inserts a new billing order into the repository.
 *
 * @param billingOrder the billing order to be created
 * @return the created billing order with a generated ID
 */
    BillingOrder save(BillingOrder billingOrder);

    /**
     * Retrieves billing orders for a client by period.
     *
     * @param clientId  the ID of the client
     * @param startDate the start date of the period
     * @param endDate   the end date of the period
     * @return list of billing orders
     */
    List<BillingOrder> receiveForUserByPeriod(String clientId, LocalDate startDate, LocalDate endDate);
}
