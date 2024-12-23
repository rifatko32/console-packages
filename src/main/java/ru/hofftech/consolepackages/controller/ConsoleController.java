package ru.hofftech.consolepackages.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.PackageFromFilePlaceService;
import ru.hofftech.consolepackages.service.TruckToPackagesService;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmType;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {
    private final PackageFromFilePlaceService packagePlaceService;
    private final TruckToPackagesService truckToPackagesService;
    private final static Integer DEFAULT_TRUCK_COUNT = 10;
    private final static String EXIT_COMMAND = "exit";
    private final Pattern TRUCK_COUNT_PATTERN = Pattern.compile("\\d+$");
    private final Pattern IMPORT_TXT_TO_CONSOLE_COMMAND_PATTERN = Pattern.compile("import_txt_to_console (.+\\.txt) \\d*");
    private final Pattern IMPORT_TXT_TO_JSON_COMMAND_PATTERN = Pattern.compile("import_txt_to_json (.+\\.txt) \\d*");
    private final Pattern IMPORT_JSON_TRUCKS_TO_TXT_PACKAGES_COMMAND_PATTERN = Pattern.compile("import_json_trucks_to_txt_packages (.+\\.json)");
    private final ReportWriterFactory reportWriterFactory;

    public void listen() {
        var scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            var strCommand = scanner.nextLine();
            if (strCommand.equals(EXIT_COMMAND)) {
                System.exit(0);
            }

            Matcher txtMatcher = IMPORT_TXT_TO_CONSOLE_COMMAND_PATTERN.matcher(strCommand);
            Matcher txtToJsonMatcher = IMPORT_TXT_TO_JSON_COMMAND_PATTERN.matcher(strCommand);
            Matcher jsonTrucksToTxtPackagesMatcher = IMPORT_JSON_TRUCKS_TO_TXT_PACKAGES_COMMAND_PATTERN.matcher(strCommand);

            var truckCount = readTruckCount(strCommand);

            if (txtMatcher.matches()) {
                executePlaceCommand(
                        txtMatcher,
                        PackagePlaceAlgorithmType.PACKAGE_PLACE_BY_WIDTH,
                        ReportEngineType.STRING,
                        ReportOutputChannelType.CONSOLE,
                        truckCount);
            } else if (txtToJsonMatcher.matches()) {
                executePlaceCommand(
                        txtToJsonMatcher,
                        PackagePlaceAlgorithmType.PACKAGE_PLACE_BY_WIDTH,
                        ReportEngineType.JSON,
                        ReportOutputChannelType.JSONFILE,
                        truckCount);
            } else if (jsonTrucksToTxtPackagesMatcher.matches()) {
                executeTruckReadingCommand(
                        jsonTrucksToTxtPackagesMatcher,
                        ReportEngineType.STRING,
                        ReportOutputChannelType.TXT_FILE);
            } else {
                log.error("Invalid command: {}", strCommand);
            }
        }
    }

    private Integer readTruckCount(String command) {
        Matcher truckCountMatcher = TRUCK_COUNT_PATTERN.matcher(command);
        var truckCount = DEFAULT_TRUCK_COUNT;
        if (truckCountMatcher.find()) {
            truckCount = Integer.parseInt(truckCountMatcher.group(0));
        }
        return truckCount;
    }

    private void executePlaceCommand(
            Matcher matcher,
            PackagePlaceAlgorithmType packagePlaceAlgorithmType,
            ReportEngineType reportEngineType,
            ReportOutputChannelType reportOutputChannelType,
            Integer truckCount) {

        var filePath = matcher.group(1);
        log.info("Start of handling file: {}", filePath);

        var packagePlaceReport = packagePlaceService.placePackages(
                filePath,
                packagePlaceAlgorithmType,
                reportEngineType,
                truckCount);

        var reportWriter = reportWriterFactory.createReportWriter(reportOutputChannelType, ".json");
        reportWriter.writeReport(packagePlaceReport);

        log.info("End of handling file: {}", filePath);
    }

    private void executeTruckReadingCommand(
            Matcher matcher,
            ReportEngineType reportEngineType,
            ReportOutputChannelType reportOutputChannelType) {

        var filePath = matcher.group(1);
        log.info("Start of handling file: {}", filePath);

        var report = truckToPackagesService.getTruckPackages(filePath, reportEngineType);

        var reportWriter = reportWriterFactory.createReportWriter(reportOutputChannelType, ".txt");
        reportWriter.writeReport(report);

        log.info("End of handling file: {}", filePath);
    }
}
