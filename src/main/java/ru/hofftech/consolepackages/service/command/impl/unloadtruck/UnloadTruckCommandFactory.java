package ru.hofftech.consolepackages.service.command.impl.unloadtruck;

import ch.qos.logback.core.util.StringUtil;
import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.service.billing.PackageBillingService;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandParser;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;
import ru.hofftech.consolepackages.service.report.truck.TruckUnloadingReportEngineFactory;
import ru.hofftech.consolepackages.service.truck.UnloadTruckService;
import ru.hofftech.consolepackages.service.truck.UnloadTruckServiceImpl;
import ru.hofftech.consolepackages.util.TruckJsonFileReader;

import static ru.hofftech.consolepackages.service.command.CommandParametersValidator.validateClientId;
import static ru.hofftech.consolepackages.service.command.CommandParametersValidator.validateInfile;
import static ru.hofftech.consolepackages.service.command.CommandParametersValidator.validateOutFilename;

/**
 * The class implements the factory of commands for unloading packages from trucks
 * and generating a report of the unloaded packages.
 */
@RequiredArgsConstructor
public class UnloadTruckCommandFactory implements CommandAbstractFactory {
    private static final String INFILE = "infile";
    private static final String OUTFILE = "outfile";
    private static final String WITH_COUNT = "withcount";
    private static final String CLIENT_ID = "clientid";

    private final UnloadTruckService unloadTruckService;
    private final ReportWriterFactory reportWriterFactory;
    private final TruckJsonFileReader fileReader;
    private final PackageBillingService packageBillingService;
    private final TruckUnloadingReportEngineFactory reportEngineFactory;

    /**
     * Creates a command to unload packages from trucks and generate a report of the unloaded packages.
     *
     * @param commandContext the context of the command to be executed
     * @return a command to unload packages from trucks
     */
    @Override
    public Command createCommand(CommandContext commandContext) {
        return new UnloadTrucksCommand(
                unloadTruckService,
                reportWriterFactory,
                (UnloadTruckContext) commandContext,
                fileReader,
                packageBillingService,
                reportEngineFactory);
    }

    /**
     * Creates the context of the command to unload packages from trucks and generate a report of the unloaded packages.
     *
     * @param strCommand the string with the command
     * @return the context of the command to unload packages from trucks and generate a report of the unloaded packages
     */
    @Override
    public CommandContext createCommandContext(String strCommand) {
        var commandKeyValues = CommandParser.parseCommandKeys(strCommand);

        var inFilePath = commandKeyValues.get(INFILE);
        var outFilePath = commandKeyValues.get(OUTFILE);
        var withCount = commandKeyValues.containsKey(WITH_COUNT);
        var clientId = commandKeyValues.get(CLIENT_ID);

        validateParameters(clientId, inFilePath, outFilePath);

        return UnloadTruckContext.builder()
                .inFilePath(inFilePath)
                .outFilePath(outFilePath)
                .withCount(withCount)
                .reportEngineType(withCount ? ReportEngineType.STRING_WITH_COUNT : ReportEngineType.STRING)
                .reportOutputChannelType(ReportOutputChannelType.TXT_FILE)
                .clientId(clientId)
                .build();
    }

    private static void validateParameters(String clientId, String inFilePath, String outFilePath) {
        validateClientId(clientId);
        validateInfile(inFilePath);
        validateOutFilename(outFilePath);
    }

    public CommandContext createCommandContextByParameters(
            String inFilePath,
            String outFilePath,
            String withCount,
            String clientId) {
        var withCountValue = Boolean.parseBoolean(withCount);

        if (StringUtil.isNullOrEmpty(clientId)) {
            throw new IllegalArgumentException("clientId is null or empty");
        }

        return UnloadTruckContext.builder()
                .inFilePath(inFilePath)
                .outFilePath(outFilePath)
                .withCount(withCountValue)
                .clientId(clientId)
                .reportEngineType(withCountValue ? ReportEngineType.STRING_WITH_COUNT : ReportEngineType.STRING)
                .reportOutputChannelType(ReportOutputChannelType.TXT_FILE)
                .build();
    }
}
