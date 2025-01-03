package ru.hofftech.consolepackages;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.controller.ConsoleController;
import ru.hofftech.consolepackages.datastorage.repository.impl.InMemoryPackageTypeRepository;
import ru.hofftech.consolepackages.service.PackageFromFilePlaceService;
import ru.hofftech.consolepackages.service.StartupDataStorageInitializer;
import ru.hofftech.consolepackages.service.TruckToPackagesService;
import ru.hofftech.consolepackages.service.command.AbstractFactoryProvider;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmFactory;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;
import ru.hofftech.consolepackages.service.report.packageitem.PackagePlaceReportEngineFactory;
import ru.hofftech.consolepackages.service.report.truck.TruckUnloadingReportEngineFactory;
import ru.hofftech.consolepackages.service.truck.TruckUnloadingAlgorithm;
import ru.hofftech.consolepackages.util.PackageFileReader;
import ru.hofftech.consolepackages.util.TruckJsonFileReader;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Starting console packages...");
        Main.start();
    }

    private static void start() {
        var packageTypeRepository = new InMemoryPackageTypeRepository();
        new StartupDataStorageInitializer(
                packageTypeRepository
        ).init();

        ConsoleController consoleController = new ConsoleController(
                new AbstractFactoryProvider(
                        new PackageFromFilePlaceService(
                                new PackageFileReader(),
                                new PackagePlaceAlgorithmFactory(),
                                new PackagePlaceReportEngineFactory()),
                        new TruckToPackagesService(
                                new TruckJsonFileReader(
                                        new Gson()),
                                new TruckUnloadingReportEngineFactory(),
                                new TruckUnloadingAlgorithm()
                        ),
                        new ReportWriterFactory(),
                        packageTypeRepository
                ));

        consoleController.listen();
    }
}