package ru.hofftech.consolepackages.service.command;

import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.billing.PackageBillingService;
import ru.hofftech.consolepackages.service.command.impl.billing.CreateBillingReportCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.createpackagetype.CreatePackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.deletepackagetype.DeletePackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.editpackagetype.EditPackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.exit.ExitCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.findpackagetype.FindPackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.placepackage.PlacePackageCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.unloadtruck.UnloadTruckCommandFactory;
import ru.hofftech.consolepackages.service.packageitem.PackageFromFileReader;
import ru.hofftech.consolepackages.service.packageitem.PackageFromStringReader;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmFactory;
import ru.hofftech.consolepackages.service.report.billing.UserBillingReportEngine;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;
import ru.hofftech.consolepackages.service.report.packageitem.PackagePlaceReportEngineFactory;
import ru.hofftech.consolepackages.service.truck.TruckToPackagesService;
import ru.hofftech.consolepackages.util.TruckJsonFileReader;

import java.util.Map;

/**
 * The class provides a map of command abstract factories and returns a factory by a command type.
 */
public class AbstractFactoryProvider {

    private final Map<CommandType, CommandAbstractFactory> abstractFactoryMap;

    public AbstractFactoryProvider(
            PackageFromFileReader packageFromFileReader,
            PackageFromStringReader packageFromStringReader,
            TruckToPackagesService truckToPackagesService,
            ReportWriterFactory reportWriterFactory,
            PackageTypeRepository packageTypeRepository,
            PackagePlaceAlgorithmFactory placeEngineFactory,
            PackagePlaceReportEngineFactory reportEngineFactory,
            TruckJsonFileReader fileReader,
            PackageBillingService packageBillingService,
            UserBillingReportEngine userBillingReportEngine
    ) {

        abstractFactoryMap = Map.of(CommandType.LOAD_PACKAGES, new PlacePackageCommandFactory(
                        packageFromFileReader,
                        reportWriterFactory,
                        placeEngineFactory,
                        reportEngineFactory,
                        packageFromStringReader,
                        packageBillingService),
                CommandType.UNLOAD_TRUCK, new UnloadTruckCommandFactory(truckToPackagesService, reportWriterFactory, fileReader, packageBillingService),
                CommandType.CREATE_PACKAGE_TYPE, new CreatePackageTypeCommandFactory(packageTypeRepository),
                CommandType.FIND_PACKAGE_TYPE, new FindPackageTypeCommandFactory(packageTypeRepository, reportWriterFactory),
                CommandType.DELETE_PACKAGE_TYPE, new DeletePackageTypeCommandFactory(packageTypeRepository),
                CommandType.EDIT_PACKAGE_TYPE, new EditPackageTypeCommandFactory(packageTypeRepository),
                CommandType.USER_BILLING_REPORT, new CreateBillingReportCommandFactory(userBillingReportEngine),
                CommandType.EXIT, new ExitCommandFactory());
    }

    /**
     * Returns a command abstract factory by a command type.
     *
     * @param strCommand A command to be parsed.
     * @return A command abstract factory.
     */
    public CommandAbstractFactory returnCommandAbstractFactory(String strCommand) {
        var commandType = CommandParser.parseCommandType(strCommand);
        var commandAbstractFactory = abstractFactoryMap.get(commandType);

        if (commandAbstractFactory != null) {
            return commandAbstractFactory;
        }
        throw new RuntimeException("Invalid command: " + strCommand);
    }
}
