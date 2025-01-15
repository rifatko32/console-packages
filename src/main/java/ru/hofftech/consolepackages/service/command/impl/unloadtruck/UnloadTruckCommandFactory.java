package ru.hofftech.consolepackages.service.command.impl.unloadtruck;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.service.truck.TruckToPackagesService;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandParser;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;

/**
 * The class implements the factory of commands for unloading packages from trucks
 * and generating a report of the unloaded packages.
 */
@RequiredArgsConstructor
public class UnloadTruckCommandFactory implements CommandAbstractFactory {
    private static final String INFILE = "infile";
    private static final String OUTFILE = "outfile";
    private static final String WITH_COUNT = "withcount";

    private final TruckToPackagesService truckToPackagesService;
    private final ReportWriterFactory reportWriterFactory;

    /**
     * Creates a command for unloading packages from trucks and generating a report of the unloaded packages.
     *
     * @param commandContext Context of the command.
     * @return A command for unloading packages from trucks and generating a report of the unloaded packages.
     */
    @Override
    public Command createCommand(CommandContext commandContext) {
        return new UnloadTrucksCommand(truckToPackagesService, reportWriterFactory, (UnloadTruckContext) commandContext);
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
        return new UnloadTruckContext(
                inFilePath,
                withCount ? ReportEngineType.STRING_WITH_COUNT : ReportEngineType.STRING,
                ReportOutputChannelType.TXT_FILE,
                outFilePath,
                withCount);
    }
}
