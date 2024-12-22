package ru.hofftech.consolepackages.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.PackageFromFilePlaceService;
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
    private final static Integer DEFAULT_TRUCK_COUNT = 10;
    private final static String EXIT_COMMAND = "exit";
    private final Pattern TRUCK_COUNT_PATTERN = Pattern.compile("\\d+$");
    private final Pattern IMPORT_TXT_TO_CONSOLE_COMMAND_PATTERN = Pattern.compile("import_txt_to_console (.+\\.txt) \\d*");
    private final Pattern IMPORT_TXT_TO_JSON_COMMAND_PATTERN = Pattern.compile("import_txt_to_json (.+\\.txt) \\d*");
    private final ReportWriterFactory reportWriterFactory;

    public void listen() {
        var scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals(EXIT_COMMAND)) {
                System.exit(0);
            }

            Matcher txtMatcher = IMPORT_TXT_TO_CONSOLE_COMMAND_PATTERN.matcher(command);
            Matcher txtToJsonMatcher = IMPORT_TXT_TO_JSON_COMMAND_PATTERN.matcher(command);

            var truckCount = readTruckCount(command);

            if (txtMatcher.matches()) {
                executeCommand(
                        txtMatcher,
                        PackagePlaceAlgorithmType.PACKAGE_PLACE_BY_WIDTH,
                        ReportEngineType.STRING,
                        ReportOutputChannelType.CONSOLE,
                        truckCount);
            } else if (txtToJsonMatcher.matches()) {
                executeCommand(
                        txtToJsonMatcher,
                        PackagePlaceAlgorithmType.PACKAGE_PLACE_BY_WIDTH,
                        ReportEngineType.JSON,
                        ReportOutputChannelType.JSONFILE,
                        truckCount);
            } else {
                log.error("Invalid command: {}", command);
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

    private void executeCommand(
            Matcher matcher,
            PackagePlaceAlgorithmType packagePlaceAlgorithmType,
            ReportEngineType reportEngineType,
            ReportOutputChannelType reportOutputChannelType,
            Integer truckCount) {
        String filePath = matcher.group(1);
        log.info("Start of handling file: {}", filePath);
        var packagePlaceReport = packagePlaceService.placePackages(
                filePath,
                packagePlaceAlgorithmType,
                reportEngineType,
                truckCount);

        var reportWriter = reportWriterFactory.createReportWriter(reportOutputChannelType);
        reportWriter.writeReport(packagePlaceReport);

        log.info("End of handling file: {}", filePath);
    }
}
