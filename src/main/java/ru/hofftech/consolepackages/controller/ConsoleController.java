package ru.hofftech.consolepackages.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.PackageFromFilePlaceService;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmType;
import ru.hofftech.consolepackages.util.ReportToConsoleWriter;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {
    private final PackageFromFilePlaceService packagePlaceService;
    private final static String EXIT_COMMAND = "exit";
    private final Pattern IMPORT_COMMAND_PATTERN = Pattern.compile("import (.+\\.txt)");
    private final ReportToConsoleWriter reportToConsoleWriter;

    public void listen() {
        var scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals(EXIT_COMMAND)) {
                System.exit(0);
            }

            Matcher matcher = IMPORT_COMMAND_PATTERN.matcher(command);
            if (matcher.matches()) {
                String filePath = matcher.group(1);
                log.info("Start of handling file: {}", filePath);
                var packagePlaceReport = packagePlaceService.placePackages(filePath, PackagePlaceAlgorithmType.PACKAGE_PLACE_BY_WIDTH);
                reportToConsoleWriter.writeReportToConsole(packagePlaceReport);
                log.info("End of handling file: {}", filePath);
            } else {
                log.error("Invalid command: {}", command);
            }
        }
    }
}
