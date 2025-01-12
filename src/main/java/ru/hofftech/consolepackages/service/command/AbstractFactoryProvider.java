package ru.hofftech.consolepackages.service.command;

import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.packageitem.PackageFromFileReader;
import ru.hofftech.consolepackages.service.packageitem.PackageFromStringReader;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmFactory;
import ru.hofftech.consolepackages.service.report.packageitem.PackagePlaceReportEngineFactory;
import ru.hofftech.consolepackages.service.truck.TruckToPackagesService;
import ru.hofftech.consolepackages.service.command.impl.createpackagetype.CreatePackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.deletepackagetype.DeletePackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.editpackagetype.EditPackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.exit.ExitCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.findpackagetype.FindPackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.placepackage.PlacePackageCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.unloadtruck.UnloadTruckCommandFactory;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * The class provides a map of command abstract factories and returns a factory by a command type.
 */
public class AbstractFactoryProvider {

    private static final Map<CommandType, CommandAbstractFactory> abstractFactoryMap = new HashMap<>();

    public AbstractFactoryProvider(
            PackageFromFileReader packageFromFileReader,
            PackageFromStringReader packageFromStringReader,
            TruckToPackagesService truckToPackagesService,
            ReportWriterFactory reportWriterFactory,
            PackageTypeRepository packageTypeRepository,
            PackagePlaceAlgorithmFactory placeEngineFactory,
            PackagePlaceReportEngineFactory reportEngineFactory
    ){

        abstractFactoryMap.put(CommandType.LOAD_PACKAGES, new PlacePackageCommandFactory(
                packageFromFileReader,
                reportWriterFactory,
                placeEngineFactory,
                reportEngineFactory,
                packageFromStringReader));
        abstractFactoryMap.put(CommandType.UNLOAD_TRUCK, new UnloadTruckCommandFactory(truckToPackagesService, reportWriterFactory));
        abstractFactoryMap.put(CommandType.CREATE_PACKAGE_TYPE, new CreatePackageTypeCommandFactory(packageTypeRepository));
        abstractFactoryMap.put(CommandType.FIND_PACKAGE_TYPE, new FindPackageTypeCommandFactory(packageTypeRepository, reportWriterFactory));
        abstractFactoryMap.put(CommandType.DELETE_PACKAGE_TYPE, new DeletePackageTypeCommandFactory(packageTypeRepository));
        abstractFactoryMap.put(CommandType.EDIT_PACKAGE_TYPE, new EditPackageTypeCommandFactory(packageTypeRepository));
        abstractFactoryMap.put(CommandType.EXIT, new ExitCommandFactory());
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
