package ru.hofftech.consolepackages.service.command;

import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.packageitem.PackageFromFilePlaceService;
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

public class AbstractFactoryProvider {

    private static final Map<CommandType, CommandAbstractFactory> abstractFactoryMap = new HashMap<>();

    public AbstractFactoryProvider(
            PackageFromFilePlaceService packagePlaceService,
            TruckToPackagesService truckToPackagesService,
            ReportWriterFactory reportWriterFactory,
            PackageTypeRepository packageTypeRepository){

        abstractFactoryMap.put(CommandType.LOAD_PACKAGES, new PlacePackageCommandFactory(packagePlaceService, reportWriterFactory));
        abstractFactoryMap.put(CommandType.UNLOAD_TRUCK, new UnloadTruckCommandFactory(truckToPackagesService, reportWriterFactory));
        abstractFactoryMap.put(CommandType.CREATE_PACKAGE_TYPE, new CreatePackageTypeCommandFactory(packageTypeRepository));
        abstractFactoryMap.put(CommandType.FIND_PACKAGE_TYPE, new FindPackageTypeCommandFactory(packageTypeRepository));
        abstractFactoryMap.put(CommandType.DELETE_PACKAGE_TYPE, new DeletePackageTypeCommandFactory(packageTypeRepository));
        abstractFactoryMap.put(CommandType.EDIT_PACKAGE_TYPE, new EditPackageTypeCommandFactory(packageTypeRepository));
        abstractFactoryMap.put(CommandType.EXIT, new ExitCommandFactory());
    }

    public CommandAbstractFactory returnCommandAbstractFactory(String strCommand) {
        var commandType = CommandParser.parseCommandType(strCommand);
        var commandAbstractFactory = abstractFactoryMap.get(commandType);

        if (commandAbstractFactory != null) {
            return commandAbstractFactory;
        }
        throw new RuntimeException("Invalid command: " + strCommand);
    }
}
