package ru.hofftech.consolepackages.service.command;

import ru.hofftech.consolepackages.service.PackageFromFilePlaceService;
import ru.hofftech.consolepackages.service.TruckToPackagesService;
import ru.hofftech.consolepackages.service.command.impl.createpackage.CreatePackageCommandFactory;
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
            ReportWriterFactory reportWriterFactory){

        abstractFactoryMap.put(CommandType.LOAD_PACKAGES, new PlacePackageCommandFactory(packagePlaceService, reportWriterFactory));
        abstractFactoryMap.put(CommandType.UNLOAD_TRUCK, new UnloadTruckCommandFactory(truckToPackagesService, reportWriterFactory));
        abstractFactoryMap.put(CommandType.CREATE_PACKAGE, new CreatePackageCommandFactory());
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
