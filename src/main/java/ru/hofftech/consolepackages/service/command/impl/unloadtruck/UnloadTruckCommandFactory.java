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
    private final TruckToPackagesService truckToPackagesService;
    private final ReportWriterFactory reportWriterFactory;

    @Override
    public Command createCommand(CommandContext commandContext) {
        return new UnloadTrucksCommand(truckToPackagesService, reportWriterFactory, (UnloadTruckContext) commandContext);
    }

    @Override
    public CommandContext createCommandContext(String strCommand) {
        var filePath = CommandParser.readFilePath(strCommand, CommandParser.parseCommandType(strCommand));
        return new UnloadTruckContext(filePath, ReportEngineType.STRING, ReportOutputChannelType.TXT_FILE, "txt");
    }
}
