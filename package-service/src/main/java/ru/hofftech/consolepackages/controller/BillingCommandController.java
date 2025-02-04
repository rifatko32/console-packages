package ru.hofftech.consolepackages.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.hofftech.consolepackages.service.command.CommandContextWithResult;
import ru.hofftech.consolepackages.service.command.impl.billing.CreateBillingReportCommandFactory;

import java.text.ParseException;

import static ru.hofftech.consolepackages.service.command.CommandConstants.BILLING_COMMAND_PATTERN;
import static ru.hofftech.consolepackages.shellconstants.CommandOptions.CLIENT_ID;
import static ru.hofftech.consolepackages.shellconstants.CommandOptions.FROM;
import static ru.hofftech.consolepackages.shellconstants.CommandOptions.TO;

/**
 * This component provides methods for Spring Shell to execute commands.
 * <p>
 * Spring Shell will find all methods annotated with {@link ShellMethod} and
 * expose them as commands.
 * </p>
 */
@RequiredArgsConstructor
@ShellComponent
public class BillingCommandController {

    private final CreateBillingReportCommandFactory createBillingReportCommandFactory;

    /**
     * This method provides a command to return billing report for a user
     * within a specified period.
     * <p>
     * The command uses the {@link CreateBillingReportCommandFactory} service to
     * generate a report of billing orders for a user by the given period.
     * </p>
     *
     * @param clientId the user id
     * @param fromDate the start date of the period
     * @param toDate   the end date of the period
     * @return a string representation of the billing report
     * @throws ParseException if the dates are invalid
     */
    @ShellMethod(key = BILLING_COMMAND_PATTERN)
    public String returnBillingByUser(
            @ShellOption(value = {CLIENT_ID}) String clientId,
            @ShellOption(value = {FROM}) String fromDate,
            @ShellOption(value = {TO}) String toDate
    ) throws ParseException {

        var context = createBillingReportCommandFactory.createCommandContextByParameters(clientId, fromDate, toDate);
        var command = createBillingReportCommandFactory.createCommand(context);
        command.execute();

        var result = ((CommandContextWithResult<?>) context).getResult();
        return result.toString();
    }
}
