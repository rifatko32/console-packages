package ru.hofftech.consolepackages.datastorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hofftech.consolepackages.datastorage.model.entity.BillingOrder;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository to work with billing orders.
 * <p>
 * This repository provides methods to create a billing order and to receive billing orders for a user by period.
 * </p>
 */
@Repository
public interface BillingOrderRepository  extends JpaRepository<BillingOrder, Long> {

    /**
     * Retrieves billing orders for a client by period.
     *
     * @param clientId  the ID of the client
     * @param startDate the start date of the period
     * @param endDate   the end date of the period
     * @return list of billing orders
     */
    @Query("select billingOrder from BillingOrder billingOrder " +
            "where billingOrder.clientId = :clientId " +
            "and billingOrder.orderDate between :startDate and :endDate")
    List<BillingOrder> receiveForUserByPeriod(String clientId, LocalDate startDate, LocalDate endDate);
}
