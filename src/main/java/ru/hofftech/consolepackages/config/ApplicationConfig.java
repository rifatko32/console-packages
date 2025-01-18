package ru.hofftech.consolepackages.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.datastorage.repository.impl.InMemoryPackageTypeRepository;
import ru.hofftech.consolepackages.service.StartupDataStorageInitializer;
import ru.hofftech.consolepackages.service.command.AbstractFactoryProvider;
import ru.hofftech.consolepackages.service.command.CommandReader;
import ru.hofftech.consolepackages.service.packageitem.PackageFactory;
import ru.hofftech.consolepackages.service.packageitem.PackageFromFileReader;
import ru.hofftech.consolepackages.service.packageitem.PackageFromStringReader;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmFactory;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;
import ru.hofftech.consolepackages.service.report.packageitem.PackagePlaceReportEngineFactory;
import ru.hofftech.consolepackages.service.report.truck.TruckUnloadingReportEngineFactory;
import ru.hofftech.consolepackages.service.truck.TruckToPackagesService;
import ru.hofftech.consolepackages.service.truck.TruckUnloadingAlgorithm;
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
                packageFromFileReader(),
                packageFromStringReader(),
                truckToPackagesService(),
                reportWriterFactory(),
                packageTypeRepository(),
                packagePlaceAlgorithmFactory(),
                packagePlaceReportEngineFactory()
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
                truckJsonFileReader(),
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
}
