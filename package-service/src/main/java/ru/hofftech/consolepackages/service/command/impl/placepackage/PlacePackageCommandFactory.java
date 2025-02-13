package ru.hofftech.consolepackages.service.command.impl.placepackage;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.service.outbox.OutboxMessageService;
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

import static ru.hofftech.consolepackages.service.command.CommandParametersValidator.validateClientId;
import static ru.hofftech.consolepackages.service.command.CommandParametersValidator.validatePackagesTextFilePath;
import static ru.hofftech.consolepackages.service.command.CommandParametersValidator.validateTrucks;
import static ru.hofftech.consolepackages.service.packageitem.PlacePackageConst.CLIENT_ID;
import static ru.hofftech.consolepackages.service.packageitem.PlacePackageConst.OUT_FILENAME_KEY;
import static ru.hofftech.consolepackages.service.packageitem.PlacePackageConst.OUT_JSON_FILE_VALUE;
import static ru.hofftech.consolepackages.service.packageitem.PlacePackageConst.OUT_KEY;
import static ru.hofftech.consolepackages.service.packageitem.PlacePackageConst.PACKAGES_FILE_KEY;
import static ru.hofftech.consolepackages.service.packageitem.PlacePackageConst.PACKAGES_TEXT_KEY;
import static ru.hofftech.consolepackages.service.packageitem.PlacePackageConst.TRUCKS_DELIMITER;
import static ru.hofftech.consolepackages.service.packageitem.PlacePackageConst.TRUCKS_KEY;
import static ru.hofftech.consolepackages.service.packageitem.PlacePackageConst.TYPE_KEY;

/**
 * The class implements the factory of commands for placing packages into trucks.
 */
@RequiredArgsConstructor
public class PlacePackageCommandFactory implements CommandAbstractFactory {
    private final PackageFromFileReader packageFromFileReader;
    private final ReportWriterFactory reportWriterFactory;
    private final PackagePlaceAlgorithmFactory placeEngineFactory;
    private final PackagePlaceReportEngineFactory reportEngineFactory;
    private final PackageFromStringReader packageFromStringReader;
    private final OutboxMessageService packageBillingService;

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
                reportEngineFactory,
                packageBillingService);
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
        var clientId = commandKeyValues.get(CLIENT_ID);

        validateTrucks(trucks);
        validatePackagesTextFilePath(packagesText, filePath);
        validateClientId(clientId);

        return PlacePackageContext.builder()
                .trucks(trucks)
                .algorithmType(algorithmType)
                .filePath(filePath)
                .reportEngineType(reportEngineType)
                .reportOutputChannelType(channelType)
                .outputFileName(outputFileName)
                .packagesText(packagesText)
                .clientId(clientId)
                .build();
    }

    /**
     * Creates the context of the command to place packages into trucks by parameters.
     *
     * @param trucks         comma-separated list of truck names
     * @param algorithmType  type of the algorithm to use while placing packages
     * @param filePath       path to the file with packages
     * @param outputFileName name of the file to write the report to
     * @param outParameter   parameter to specify the report output channel
     * @param packagesText   text with packages to place
     * @return the context of the command to place packages into trucks
     */
    public CommandContext createCommandContextByParameters(
            String trucks,
            String algorithmType,
            String filePath,
            String outParameter,
            String outputFileName,
            String packagesText,
            String clientId) {

        validatePackagesTextFilePath(packagesText, filePath);

        return PlacePackageContext.builder()
                .trucks(Arrays.stream(trucks.split(TRUCKS_DELIMITER)).toList())
                .algorithmType(PackagePlaceAlgorithmType.fromLabel(algorithmType))
                .filePath(filePath)
                .reportEngineType(Objects.equals(outParameter, OUT_JSON_FILE_VALUE) ? ReportEngineType.JSON : ReportEngineType.STRING)
                .reportOutputChannelType(ReportOutputChannelType.fromLabel(outParameter))
                .outputFileName(outputFileName)
                .packagesText(packagesText)
                .clientId(clientId)
                .build();
    }
}
