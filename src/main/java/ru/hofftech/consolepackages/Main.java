package ru.hofftech.consolepackages;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.controller.ConsoleController;
import ru.hofftech.consolepackages.datastorage.repository.impl.InMemoryPackageTypeRepository;
import ru.hofftech.consolepackages.service.command.CommandReader;
import ru.hofftech.consolepackages.service.StartupDataStorageInitializer;
import ru.hofftech.consolepackages.service.command.AbstractFactoryProvider;
import ru.hofftech.consolepackages.service.packageitem.PackageFactory;
import ru.hofftech.consolepackages.service.packageitem.PackageFromFileReader;
import ru.hofftech.consolepackages.service.packageitem.PackageFromStringReader;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmFactory;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;
import ru.hofftech.consolepackages.service.report.packageitem.PackagePlaceReportEngineFactory;
import ru.hofftech.consolepackages.service.report.truck.TruckUnloadingReportEngineFactory;
import ru.hofftech.consolepackages.service.truck.TruckToPackagesService;
import ru.hofftech.consolepackages.service.truck.TruckUnloadingAlgorithm;
import ru.hofftech.consolepackages.telegram.PackageTelegramBot;
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
        ).createDefaultPackageTypes();

        var commandReader = new CommandReader(
                new AbstractFactoryProvider(
                        new PackageFromFileReader(
                                new PackageFileReader(),
                                new PackageFactory(packageTypeRepository)),
                        new PackageFromStringReader(
                                new PackageFactory(packageTypeRepository)),
                        new TruckToPackagesService(
                                new TruckJsonFileReader(
                                        new Gson()),
                                new TruckUnloadingReportEngineFactory(),
                                new TruckUnloadingAlgorithm()
                        ),
                        new ReportWriterFactory(),
                        packageTypeRepository,
                        new PackagePlaceAlgorithmFactory(),
                        new PackagePlaceReportEngineFactory()
                ));

        PackageTelegramBot.registerTelegramBot(commandReader);

        ConsoleController consoleController = new ConsoleController(commandReader);

        consoleController.listen();
    }
}