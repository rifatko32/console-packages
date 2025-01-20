package ru.hofftech.consolepackages.service.command.impl.billing;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellOption;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandParser;
import ru.hofftech.consolepackages.service.report.billing.UserBillingReportEngine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
public class CreateBillingReportCommandFactory implements CommandAbstractFactory {
    private static final String USERID = "user";
    private static final String FROM_DATE = "from";
    private static final String TO_DATE = "to";
    private static final String DATEFORMAT = "dd.MM.yyyy";

    private final UserBillingReportEngine userBillingReportEngine;

    @Override
    public Command createCommand(CommandContext commandContext) {
        return new CreateBillingReportCommand((CreateBillingReportContext) commandContext, userBillingReportEngine);
    }

    @Override
    public CommandContext createCommandContext(String strCommand) throws ParseException {
        var commandKeyValues = CommandParser.parseCommandKeys(strCommand);

        var userId = commandKeyValues.get(USERID);
        var fromDate = parseDate(commandKeyValues.get(FROM_DATE));
        var toDate = parseDate(commandKeyValues.get(TO_DATE));

        return new CreateBillingReportContext.Builder()
                .userid(userId)
                .fromDate(fromDate)
                .toDate(toDate)
                .build();
    }

    private static Date parseDate(String dateString) throws ParseException {
        var sdf = new SimpleDateFormat(DATEFORMAT);
        return sdf.parse(dateString);
    }

/**
 * Creates a command context for generating a billing report by parameters.
 *
 * @param userId       the ID of the user for whom to generate the report
 * @param fromDateStr  the start date of the period as a string
 * @param toDateStr    the end date of the period as a string
 * @return a {@link CreateBillingReportContext} with the specified parameters
 * @throws ParseException if the date strings cannot be parsed
 */
    public CommandContext createCommandContextByParameters(String userId, String fromDateStr, String toDateStr) throws ParseException {
        var fromDate = parseDate(fromDateStr);
        var toDate = parseDate(toDateStr);

        return new CreateBillingReportContext.Builder()
                .userid(userId)
                .fromDate(fromDate)
                .toDate(toDate)
                .build();
    }
}
