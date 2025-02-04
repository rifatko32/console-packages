package ru.hofftech.billing.service;

import ru.hofftech.billing.model.dto.BillingResponse;
import ru.hofftech.billing.model.dto.CreatePackageBillRequest;

import java.time.LocalDate;
import java.util.List;

/**
 * Service interface for package billing operations.
 * <p>
 * This service provides methods for creating package bills and retrieving billing summaries.
 * </p>
 */
public interface PackageBillingService {

    /**
     * Creates a package bill based on the provided request.
     *
     * @param createPackageBillRequest the request containing the details for creating the package bill
     */
    void creatPackageBill(CreatePackageBillRequest createPackageBillRequest);

    /**
     * Retrieves a billing summary for a user within a specified period.
     *
     * @param clientId the user id
     * @param fromDate the start date of the period
     * @param toDate   the end date of the period
     * @return a list of {@link BillingResponse} objects
     */
    List<BillingResponse> returnBillingSummaryByClient(String clientId, LocalDate fromDate, LocalDate toDate);
}
