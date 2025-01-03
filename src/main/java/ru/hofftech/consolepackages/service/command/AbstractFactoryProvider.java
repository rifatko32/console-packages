package ru.hofftech.consolepackages.service.command;

import ru.hofftech.consolepackages.service.PackageFromFilePlaceService;
import ru.hofftech.consolepackages.service.TruckToPackagesService;
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

        abstractFactoryMap.put(CommandType.PLACE_PACKAGES_FROM_TXT_FILE_TO_CONSOLE, new PlacePackageCommandFactory(packagePlaceService, reportWriterFactory));
        abstractFactoryMap.put(CommandType.PLACE_PACKAGES_FROM_TXT_FILE_TO_JSON_FILE, new PlacePackageCommandFactory(packagePlaceService, reportWriterFactory));
        abstractFactoryMap.put(CommandType.UNLOAD_TRUCKS_FROM_JSON_TO_TXT_FILE, new UnloadTruckCommandFactory(truckToPackagesService, reportWriterFactory));
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
