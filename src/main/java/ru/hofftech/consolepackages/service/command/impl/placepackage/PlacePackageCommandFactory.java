package ru.hofftech.consolepackages.service.command.impl.placepackage;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandParser;
import ru.hofftech.consolepackages.service.packageitem.PackageFromFileReader;
import ru.hofftech.consolepackages.service.packageitem.PackageFromStringReader;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmFactory;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmType;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;
import ru.hofftech.consolepackages.service.report.packageitem.PackagePlaceReportEngineFactory;

import java.util.Arrays;
import java.util.Objects;

/**
 * The class implements the factory of commands for placing packages into trucks.
 */
@RequiredArgsConstructor
public class PlacePackageCommandFactory implements CommandAbstractFactory {
    private static final String OUT_KEY = "out";
    private static final String TYPE_KEY = "type";
    private static final String TRUCKS_KEY = "trucks";
    private static final String TRUCKS_DELIMITER = ";";
    private static final String PACKAGES_FILE_KEY = "packages-file";
    private static final String PACKAGES_TEXT_KEY = "packages-text";
    private static final String OUT_FILENAME_KEY = "out-filename";
    private static final String OUT_JSON_FILE_VALUE = "json-file";


    private final PackageFromFileReader packageFromFileReader;
    private final ReportWriterFactory reportWriterFactory;
    private final PackagePlaceAlgorithmFactory placeEngineFactory;
    private final PackagePlaceReportEngineFactory reportEngineFactory;
    private final PackageFromStringReader packageFromStringReader;


    /**
     * Creates a command for placing packages into trucks.
     *
     * @param commandContext Context of the command.
     * @return A command for placing packages into trucks.
     */
    @Override
    public Command createCommand(CommandContext commandContext) {
        return new PlacePackagesCommand(
                packageFromFileReader,
                packageFromStringReader,
                reportWriterFactory,
                (PlacePackageContext) commandContext,
                placeEngineFactory,
                reportEngineFactory);
    }


    /**
     * Creates the context of the command to place packages into trucks.
     *
     * @param strCommand the string with the command
     * @return the context of the command to place packages into trucks
     */
    @Override
    public CommandContext createCommandContext(String strCommand) {

        var commandKeyValues = CommandParser.parseCommandKeys(strCommand);

        var trucks = Arrays.stream(commandKeyValues.get(TRUCKS_KEY).split(TRUCKS_DELIMITER)).toList();
        var algorithmType = PackagePlaceAlgorithmType.fromLabel(commandKeyValues.get(TYPE_KEY));
        var filePath = commandKeyValues.get(PACKAGES_FILE_KEY);
        var packagesText = commandKeyValues.get(PACKAGES_TEXT_KEY);
        var channelType = ReportOutputChannelType.fromLabel(commandKeyValues.get(OUT_KEY));
        var reportEngineType = Objects.equals(commandKeyValues.get(OUT_KEY), OUT_JSON_FILE_VALUE) ? ReportEngineType.JSON : ReportEngineType.STRING;
        var outputFileName = commandKeyValues.get(OUT_FILENAME_KEY);

        return new PlacePackageContext(trucks, algorithmType, filePath, reportEngineType, channelType, outputFileName, packagesText);
    }
}
