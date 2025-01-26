package ru.hofftech.consolepackages.service.command.impl.billing;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.report.billing.UserBillingReportEngine;

/**
 * Command to create a billing report for a user within a specified period.
 * <p>
 * This command uses the {@link UserBillingReportEngine} service to generate a report
 * of billing orders for a user by the given period. The result is set in the
 * {@link CreateBillingReportContext}.
 * </p>
 *
 * @see Command#execute()
 */
@RequiredArgsConstructor
public class CreateBillingReportCommand implements Command {

    private final CreateBillingReportContext context;
    private final UserBillingReportEngine userBillingReport;

    /**
     * Executes the command.
     *
     * @see Command#execute()
     */
    @Override
    public void execute() {
        var report = userBillingReport.generateByPeriod(context.getUserid(), context.getFromDate(), context.getToDate());

        context.setResult(report.toPlainString());
    }
}
