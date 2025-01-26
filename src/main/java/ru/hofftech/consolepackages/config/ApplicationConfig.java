package ru.hofftech.consolepackages.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hofftech.consolepackages.datastorage.repository.BillingOrderRepository;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.datastorage.repository.impl.InMemoryBillingOrderRepository;
import ru.hofftech.consolepackages.datastorage.repository.impl.InMemoryPackageTypeRepository;
import ru.hofftech.consolepackages.service.StartupDataStorageInitializer;
import ru.hofftech.consolepackages.service.billing.PackageBillingService;
import ru.hofftech.consolepackages.service.billing.PackageBillingServiceImpl;
import ru.hofftech.consolepackages.service.command.AbstractFactoryProvider;
import ru.hofftech.consolepackages.service.command.CommandReader;
import ru.hofftech.consolepackages.service.command.impl.billing.CreateBillingReportCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.createpackagetype.CreatePackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.deletepackagetype.DeletePackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.editpackagetype.EditPackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.exit.ExitCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.findpackagetype.FindPackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.placepackage.PlacePackageCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.unloadtruck.UnloadTruckCommandFactory;
import ru.hofftech.consolepackages.service.packageitem.PackageFactory;
import ru.hofftech.consolepackages.service.packageitem.PackageFromFileReader;
import ru.hofftech.consolepackages.service.packageitem.PackageFromStringReader;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmFactory;
import ru.hofftech.consolepackages.service.report.billing.UserBillingReportEngine;
import ru.hofftech.consolepackages.service.report.billing.UserBillingReportImpl;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;
import ru.hofftech.consolepackages.service.report.packageitem.PackagePlaceReportEngineFactory;
import ru.hofftech.consolepackages.service.report.truck.TruckUnloadingReportEngineFactory;
import ru.hofftech.consolepackages.service.truck.TruckToPackagesService;
import ru.hofftech.consolepackages.service.truck.TruckUnloadingAlgorithm;
import ru.hofftech.consolepackages.telegram.PackageTelegramBot;
import ru.hofftech.consolepackages.util.PackageFileReader;
import ru.hofftech.consolepackages.util.TruckJsonFileReader;

@Configuration
public class ApplicationConfig {

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public AbstractFactoryProvider abstractFactoryProvider() {
        return new AbstractFactoryProvider(
                createBillingReportCommandFactory(),
                placePackageCommandFactory(),
                unloadTruckCommandFactory(),
                createPackageTypeCommandFactory(),
                findPackageTypeCommandFactory(),
                exitCommandFactory(),
                deletePackageTypeCommandFactory(),
                editPackageTypeCommandFactory()
        );
    }

    @Bean
    public StartupDataStorageInitializer startupDataStorageInitializer() {
        return new StartupDataStorageInitializer(packageTypeRepository());
    }

    @Bean
    public PackageTypeRepository packageTypeRepository() {
        return new InMemoryPackageTypeRepository();
    }

    @Bean
    public BillingOrderRepository billingOrderRepository() {
        return new InMemoryBillingOrderRepository();
    }

    @Bean
    public PackageFileReader packageFileReader() {
        return new PackageFileReader();
    }

    @Bean
    public PackageFromStringReader packageFromStringReader() {
        return new PackageFromStringReader(
                packageFactory()
        );
    }

    @Bean
    public TruckToPackagesService truckToPackagesService() {
        return new TruckToPackagesService(
                truckUnloadingReportEngineFactory(),
                truckUnloadingAlgorithm()
        );
    }

    @Bean
    public TruckUnloadingReportEngineFactory truckUnloadingReportEngineFactory() {
        return new TruckUnloadingReportEngineFactory();
    }

    @Bean
    public PackagePlaceAlgorithmFactory packagePlaceAlgorithmFactory() {
        return new PackagePlaceAlgorithmFactory();
    }

    @Bean
    public PackagePlaceReportEngineFactory packagePlaceReportEngineFactory() {
        return new PackagePlaceReportEngineFactory();
    }

    @Bean
    public ReportWriterFactory reportWriterFactory() {
        return new ReportWriterFactory();
    }

    @Bean
    public TruckUnloadingAlgorithm truckUnloadingAlgorithm() {
        return new TruckUnloadingAlgorithm();
    }

    @Bean
    public TruckJsonFileReader truckJsonFileReader() {
        return new TruckJsonFileReader(gson());
    }

    @Bean
    public PackageFactory packageFactory() {
        return new PackageFactory(packageTypeRepository());
    }

    @Bean
    public PackageFromFileReader packageFromFileReader() {
        return new PackageFromFileReader(
                packageFileReader(),
                packageFactory()
        );
    }

    @Bean
    public CommandReader commandReader() {
        return new CommandReader(abstractFactoryProvider());
    }

    @Bean
    public PackageTelegramBot packageTelegramBot() {
        return new PackageTelegramBot(commandReader());
    }

    @Bean
    public PackageBillingService packageBillingService() {
        return new PackageBillingServiceImpl(billingOrderRepository());
    }

    @Bean
    public UserBillingReportEngine userBillingReportEngine() {
        return new UserBillingReportImpl(billingOrderRepository());
    }

    @Bean
    public CreateBillingReportCommandFactory createBillingReportCommandFactory() {
        return new CreateBillingReportCommandFactory(userBillingReportEngine());
    }

    @Bean
    public PlacePackageCommandFactory placePackageCommandFactory() {
        return new PlacePackageCommandFactory(
                packageFromFileReader(),
                reportWriterFactory(),
                packagePlaceAlgorithmFactory(),
                packagePlaceReportEngineFactory(),
                packageFromStringReader(),
                packageBillingService()
        );
    }

    @Bean
    public UnloadTruckCommandFactory unloadTruckCommandFactory() {
        return new UnloadTruckCommandFactory(
                truckToPackagesService(),
                reportWriterFactory(),
                truckJsonFileReader(),
                packageBillingService()
        );
    }

    @Bean
    public CreatePackageTypeCommandFactory createPackageTypeCommandFactory(){
        return new CreatePackageTypeCommandFactory(packageTypeRepository());
    }

    @Bean
    public FindPackageTypeCommandFactory findPackageTypeCommandFactory(){
        return new FindPackageTypeCommandFactory(packageTypeRepository(), reportWriterFactory());
    }

    @Bean
    public ExitCommandFactory exitCommandFactory() {
        return new ExitCommandFactory();
    }

    @Bean
    public DeletePackageTypeCommandFactory deletePackageTypeCommandFactory() {
        return new DeletePackageTypeCommandFactory(packageTypeRepository());
    }

    @Bean
    public EditPackageTypeCommandFactory editPackageTypeCommandFactory() {
        return new EditPackageTypeCommandFactory(packageTypeRepository());
    }
}
