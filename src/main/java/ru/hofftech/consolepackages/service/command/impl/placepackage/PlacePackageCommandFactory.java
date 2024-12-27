package ru.hofftech.consolepackages.service.command.impl.placepackage;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.service.PackageFromFilePlaceService;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandParser;
import ru.hofftech.consolepackages.service.command.CommandType;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;

@RequiredArgsConstructor
public class PlacePackageCommandFactory implements CommandAbstractFactory {
    private final PackageFromFilePlaceService packagePlaceService;
    private final ReportWriterFactory reportWriterFactory;

    @Override
    public Command createCommand(CommandContext commandContext) {
        return new PlacePackagesCommand(packagePlaceService, reportWriterFactory, (PlacePackageContext) commandContext);
    }

    @Override
    public CommandContext createCommandContext(String strCommand) {
        var truckCount = CommandParser.readTruckCount(strCommand);
        var algorithmType = CommandParser.readAlgorithmName(strCommand);
        var filePath = CommandParser.readFilePath(strCommand, CommandParser.parseCommandType(strCommand));
        ReportOutputChannelType channelType = null;
        ReportEngineType reportEngineType = null;
        String fileExtension = null;

        var commandType = CommandParser.parseCommandType(strCommand);
        switch (commandType) {
            case CommandType.PLACE_PACKAGES_FROM_TXT_FILE_TO_CONSOLE:
                channelType = ReportOutputChannelType.CONSOLE;
                reportEngineType = ReportEngineType.STRING;
                break;
            case CommandType.PLACE_PACKAGES_FROM_TXT_FILE_TO_JSON_FILE:
                channelType = ReportOutputChannelType.JSONFILE;
                reportEngineType = ReportEngineType.JSON;
                fileExtension = "json";
        }

        return new PlacePackageContext(truckCount, algorithmType, filePath, reportEngineType, channelType, fileExtension);
    }
}
