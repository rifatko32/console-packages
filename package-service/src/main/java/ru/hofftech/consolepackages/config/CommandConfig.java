package ru.hofftech.consolepackages.config;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hofftech.consolepackages.datastorage.repository.OutboxMessageRepository;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.mapper.billing.BillingMapper;
import ru.hofftech.consolepackages.mapper.loadpackage.TruckMapper;
import ru.hofftech.consolepackages.service.command.AbstractFactoryProvider;
import ru.hofftech.consolepackages.service.command.CommandReader;
import ru.hofftech.consolepackages.service.command.impl.createpackagetype.CreatePackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.deletepackagetype.DeletePackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.editpackagetype.EditPackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.exit.ExitCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.findpackagetype.FindPackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.placepackage.PlacePackageCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.unloadtruck.UnloadTruckCommandFactory;
import ru.hofftech.consolepackages.service.outbox.OutboxMessageService;
import ru.hofftech.consolepackages.service.outbox.OutboxMessageServiceImpl;
import ru.hofftech.consolepackages.service.packageitem.PackageFactory;
import ru.hofftech.consolepackages.service.packageitem.PackageFromFileReader;
import ru.hofftech.consolepackages.service.packageitem.PackageFromStringReader;
import ru.hofftech.consolepackages.service.packageitem.PlacePackageService;
import ru.hofftech.consolepackages.service.packageitem.PlacePackageServiceImpl;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmFactory;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;
import ru.hofftech.consolepackages.service.report.packageitem.PackagePlaceReportEngineFactory;
import ru.hofftech.consolepackages.service.report.truck.TruckUnloadingReportEngineFactory;
import ru.hofftech.consolepackages.service.truck.TruckUnloadingAlgorithm;
import ru.hofftech.consolepackages.service.truck.UnloadTruckService;
import ru.hofftech.consolepackages.service.truck.UnloadTruckServiceImpl;
import ru.hofftech.consolepackages.stream.BillingStreamer;
import ru.hofftech.consolepackages.util.PackageFileReader;
import ru.hofftech.consolepackages.util.TruckJsonFileReader;

import java.time.Clock;

@Configuration
@RequiredArgsConstructor
public class CommandConfig {

    private final PackageTypeRepository packageTypeRepository;
    private final OutboxMessageRepository outboxMessageRepository;
    private final TruckMapper truckMapper;
    private final BillingMapper billingMapper;
    private final Clock clock;
    private final BillingStreamer billingStreamer;

    @Bean
    public AbstractFactoryProvider abstractFactoryProvider() {
        return new AbstractFactoryProvider(
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
    public CommandReader commandReader() {
        return new CommandReader(abstractFactoryProvider());
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
    public PackagePlaceReportEngineFactory packagePlaceReportEngineFactory() {
        return new PackagePlaceReportEngineFactory();
    }

    @Bean
    public UnloadTruckCommandFactory unloadTruckCommandFactory() {
        return new UnloadTruckCommandFactory(
                unloadTruckService(),
                reportWriterFactory(),
                truckJsonFileReader(),
                packageBillingService(),
                truckUnloadingReportEngineFactory());
    }

    @Bean
    public CreatePackageTypeCommandFactory createPackageTypeCommandFactory(){
        return new CreatePackageTypeCommandFactory(packageTypeRepository);
    }

    @Bean
    public FindPackageTypeCommandFactory findPackageTypeCommandFactory(){
        return new FindPackageTypeCommandFactory(packageTypeRepository, reportWriterFactory());
    }

    @Bean
    public ExitCommandFactory exitCommandFactory() {
        return new ExitCommandFactory();
    }

    @Bean
    public DeletePackageTypeCommandFactory deletePackageTypeCommandFactory() {
        return new DeletePackageTypeCommandFactory(packageTypeRepository);
    }

    @Bean
    public EditPackageTypeCommandFactory editPackageTypeCommandFactory() {
        return new EditPackageTypeCommandFactory(packageTypeRepository);
    }

    @Bean
    public PackageFromFileReader packageFromFileReader() {
        return new PackageFromFileReader(
                packageFileReader(),
                packageFactory()
        );
    }

    @Bean
    public PackageFileReader packageFileReader() {
        return new PackageFileReader();
    }

    @Bean
    public PackageFactory packageFactory() {
        return new PackageFactory(packageTypeRepository);
    }

    @Bean
    public ReportWriterFactory reportWriterFactory() {
        return new ReportWriterFactory();
    }

    @Bean
    public UnloadTruckService unloadTruckService() {
        return new UnloadTruckServiceImpl(
                truckUnloadingAlgorithm(),
                truckMapper,
                packageBillingService());
    }

    @Bean
    public TruckUnloadingAlgorithm truckUnloadingAlgorithm() {
        return new TruckUnloadingAlgorithm();
    }

    @Bean
    public PackageFromStringReader packageFromStringReader() {
        return new PackageFromStringReader(
                packageFactory()
        );
    }

    @Bean
    public PlacePackageService placePackageService() {
        return new PlacePackageServiceImpl(
                packageFromStringReader(),
                packageFactory(),
                packagePlaceAlgorithmFactory(),
                packageBillingService(),
                truckMapper);
    }

    @Bean
    public PackagePlaceAlgorithmFactory packagePlaceAlgorithmFactory() {
        return new PackagePlaceAlgorithmFactory();
    }

    @Bean
    public OutboxMessageService packageBillingService() {
        return new OutboxMessageServiceImpl(
                billingMapper,
                outboxMessageRepository,
                billingStreamer,
                clock);
    }

    @Bean
    public TruckJsonFileReader truckJsonFileReader() {
        return new TruckJsonFileReader(gson());
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public TruckUnloadingReportEngineFactory truckUnloadingReportEngineFactory() {
        return new TruckUnloadingReportEngineFactory();
    }
}
