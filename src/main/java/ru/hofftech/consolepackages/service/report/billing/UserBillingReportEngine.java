package ru.hofftech.consolepackages.service.report.billing;

import ru.hofftech.consolepackages.service.report.PlaneStringReport;

import java.util.Date;

/**
 * Generates a report of billing orders for a user by period.
 */
public interface UserBillingReportEngine {

    /**
     * Generates a report of billing orders for a user within a specified period.
     *
     * @param userId   the ID of the user for whom to generate the report
     * @param fromDate the start date of the period
     * @param toDate   the end date of the period
     * @return a {@link PlaneStringReport} containing the billing orders grouped by date and operation type
     */
    PlaneStringReport generateByPeriod(String userId, Date fromDate, Date toDate);
}
