package ru.hofftech.billing.service;

import jakarta.validation.constraints.NotNull;
import ru.hofftech.billing.model.dto.CreatePackageBillRequest;
import ru.hofftech.billing.model.dto.GenerateReportByPeriodResponse;

import java.time.LocalDate;

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
     * Generates a report of package bills for a user within a specified period.
     *
     * @param userId   the user id
     * @param fromDate the start date of the period
     * @param toDate   the end date of the period
     * @return a response containing the report
     */
    GenerateReportByPeriodResponse generateReportByPeriod(
            @NotNull
            String userId,
            @NotNull
            LocalDate fromDate,
            @NotNull
            LocalDate toDate);
}
