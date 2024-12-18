package ru.hofftech.consolepackages;

import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.controller.ConsoleController;
import ru.hofftech.consolepackages.service.PackageFromFilePlaceService;
import ru.hofftech.consolepackages.service.engine.PackagePlaceEngineFactory;
import ru.hofftech.consolepackages.service.report.PackagePlaceStringReportEngine;
import ru.hofftech.consolepackages.util.PackageFileReader;
import ru.hofftech.consolepackages.util.ReportToConsoleWriter;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Starting console packages...");
        Main.start();
    }

    private static void start() {
        ConsoleController consoleController = new ConsoleController(
                new PackageFromFilePlaceService(
                        new PackageFileReader(),
                        new PackagePlaceEngineFactory(),
                        new ReportToConsoleWriter(),
                        new PackagePlaceStringReportEngine()));
        consoleController.listen();
    }
}