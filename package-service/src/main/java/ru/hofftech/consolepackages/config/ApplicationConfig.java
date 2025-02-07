package ru.hofftech.consolepackages.config;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hofftech.consolepackages.datastorage.repository.OutboxMessageRepository;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.mapper.billing.BillingMapper;
import ru.hofftech.consolepackages.mapper.loadpackage.PackageMapper;
import ru.hofftech.consolepackages.mapper.packagetype.PackageTypeMapper;
import ru.hofftech.consolepackages.mapper.loadpackage.TruckMapper;
import ru.hofftech.consolepackages.service.outbox.OutboxMessageService;
import ru.hofftech.consolepackages.service.outbox.OutboxMessageServiceImpl;
import ru.hofftech.consolepackages.service.command.AbstractFactoryProvider;
import ru.hofftech.consolepackages.service.command.CommandReader;
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
import ru.hofftech.consolepackages.service.packageitem.PlacePackageService;
import ru.hofftech.consolepackages.service.packageitem.PlacePackageServiceImpl;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmFactory;
import ru.hofftech.consolepackages.service.packagetype.PackageTypeService;
import ru.hofftech.consolepackages.service.packagetype.PackageTypeServiceImpl;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;
import ru.hofftech.consolepackages.service.report.packageitem.PackagePlaceReportEngineFactory;
import ru.hofftech.consolepackages.service.report.truck.TruckUnloadingReportEngineFactory;
import ru.hofftech.consolepackages.service.truck.UnloadTruckService;
import ru.hofftech.consolepackages.service.truck.UnloadTruckServiceImpl;
import ru.hofftech.consolepackages.service.truck.TruckUnloadingAlgorithm;
import ru.hofftech.consolepackages.stream.BillingStreamer;
import ru.hofftech.consolepackages.telegram.PackageTelegramBot;
import ru.hofftech.consolepackages.util.PackageFileReader;
import ru.hofftech.consolepackages.util.TruckJsonFileReader;

import java.time.Clock;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final PackageTypeRepository packageTypeRepository;
    private final OutboxMessageRepository outboxMessageRepository;
    private final StreamBridge streamBridge;

    @Bean
    public Gson gson() {
        return new Gson();
    }

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
    public UnloadTruckService unloadTruckService() {
        return new UnloadTruckServiceImpl(
                truckUnloadingAlgorithm(),
                truckMapper(),
                packageBillingService());
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
        return new PackageFactory(packageTypeRepository);
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
    public OutboxMessageService packageBillingService() {
        return new OutboxMessageServiceImpl(
                billingMapper(),
                outboxMessageRepository,
                gson(),
                clock());
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
    public PackageTypeService packageTypeService() {
        return new PackageTypeServiceImpl(packageTypeRepository, packageTypeMapper());
    }

    @Bean
    public PlacePackageService placePackageService() {
        return new PlacePackageServiceImpl(
                packageFromStringReader(),
                packageFactory(),
                packagePlaceAlgorithmFactory(),
                packageBillingService(),
                truckMapper());
    }

    @Bean
    public TruckMapper truckMapper() {
        return org.mapstruct.factory.Mappers.getMapper(TruckMapper.class);
    }

    @Bean
    public BillingMapper billingMapper() {
        return org.mapstruct.factory.Mappers.getMapper(BillingMapper.class);
    }

    @Bean
    public PackageMapper packageMapper() {
        return org.mapstruct.factory.Mappers.getMapper(PackageMapper.class);
    }

    @Bean
    public PackageTypeMapper packageTypeMapper() {
        return org.mapstruct.factory.Mappers.getMapper(PackageTypeMapper.class);
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public BillingStreamer billingStreamer() {
        return new BillingStreamer(streamBridge);
    }
}
