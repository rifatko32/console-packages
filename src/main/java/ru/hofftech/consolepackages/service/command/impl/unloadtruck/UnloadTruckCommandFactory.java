package ru.hofftech.consolepackages.service.command.impl.unloadtruck;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.service.TruckToPackagesService;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandParser;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;

@RequiredArgsConstructor
public class UnloadTruckCommandFactory implements CommandAbstractFactory {
    private static final String INFILE = "infile";
    private static final String OUTFILE = "outfile";
    private static final String WITH_COUNT = "with-count";

    private final TruckToPackagesService truckToPackagesService;
    private final ReportWriterFactory reportWriterFactory;

    @Override
    public Command createCommand(CommandContext commandContext) {
        return new UnloadTrucksCommand(truckToPackagesService, reportWriterFactory, (UnloadTruckContext) commandContext);
    }

    @Override
    public CommandContext createCommandContext(String strCommand) {
        var commandKeyValues = CommandParser.parseCommandKeys(strCommand);

        var inFilePath = commandKeyValues.get(INFILE);
        var outFilePath = commandKeyValues.get(OUTFILE);
        var withCount = commandKeyValues.containsKey(WITH_COUNT);
        return new UnloadTruckContext(inFilePath, ReportEngineType.STRING, ReportOutputChannelType.TXT_FILE, outFilePath, withCount);
    }
}
