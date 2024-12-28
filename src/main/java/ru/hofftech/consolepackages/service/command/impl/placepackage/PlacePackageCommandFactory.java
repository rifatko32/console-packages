package ru.hofftech.consolepackages.service.command.impl.placepackage;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.service.PackageFromFilePlaceService;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandParser;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmType;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;

import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
public class PlacePackageCommandFactory implements CommandAbstractFactory {
    private static final String OUT_KEY = "out";
    private static final String TYPE_KEY = "type";
    private static final String TRUCKS_KEY = "trucks";
    private static final String PACKAGES_FILE_KEY = "packages-file";
    private static final String OUT_FILENAME_KEY = "out-filename";
    private static final String OUT_JSON_FILE_VALUE = "json-file";


    private final PackageFromFilePlaceService packagePlaceService;
    private final ReportWriterFactory reportWriterFactory;

    @Override
    public Command createCommand(CommandContext commandContext) {
        return new PlacePackagesCommand(packagePlaceService, reportWriterFactory, (PlacePackageContext) commandContext);
    }

    @Override
    public CommandContext createCommandContext(String strCommand) {

        var commandKeyValues = CommandParser.parseCommandKeys(strCommand);

        var trucks = Arrays.stream(commandKeyValues.get(TRUCKS_KEY).split(";")).toList();
        var algorithmType = PackagePlaceAlgorithmType.fromLabel(commandKeyValues.get(TYPE_KEY));
        var filePath = commandKeyValues.get(PACKAGES_FILE_KEY);
        var channelType = Objects.equals(commandKeyValues.get(OUT_KEY), OUT_JSON_FILE_VALUE) ? ReportOutputChannelType.JSONFILE : ReportOutputChannelType.CONSOLE;
        var reportEngineType = Objects.equals(commandKeyValues.get(OUT_KEY), OUT_JSON_FILE_VALUE) ? ReportEngineType.JSON : ReportEngineType.STRING;
        var outputFileName = commandKeyValues.get(OUT_FILENAME_KEY);

        return new PlacePackageContext(trucks, algorithmType, filePath, reportEngineType, channelType, outputFileName);
    }
}
