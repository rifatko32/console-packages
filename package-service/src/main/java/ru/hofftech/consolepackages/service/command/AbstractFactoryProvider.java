package ru.hofftech.consolepackages.service.command;

import ru.hofftech.consolepackages.service.command.impl.createpackagetype.CreatePackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.deletepackagetype.DeletePackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.editpackagetype.EditPackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.exit.ExitCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.findpackagetype.FindPackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.placepackage.PlacePackageCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.unloadtruck.UnloadTruckCommandFactory;

import java.util.Map;

/**
 * The class provides a map of command abstract factories and returns a factory by a command type.
 */
public class AbstractFactoryProvider {

    private final Map<CommandType, CommandAbstractFactory> abstractFactoryMap;

    public AbstractFactoryProvider(
            PlacePackageCommandFactory placePackageCommandFactory,
            UnloadTruckCommandFactory unloadTruckCommandFactory,
            CreatePackageTypeCommandFactory createPackageTypeCommandFactory,
            FindPackageTypeCommandFactory findPackageTypeCommandFactory,
            ExitCommandFactory exitCommandFactory,
            DeletePackageTypeCommandFactory deletePackageTypeCommandFactor,
            EditPackageTypeCommandFactory editPackageTypeCommandFactory
    ) {

        abstractFactoryMap = Map.of(CommandType.LOAD_PACKAGES, placePackageCommandFactory,
                CommandType.UNLOAD_TRUCK, unloadTruckCommandFactory,
                CommandType.CREATE_PACKAGE_TYPE, createPackageTypeCommandFactory,
                CommandType.FIND_PACKAGE_TYPE, findPackageTypeCommandFactory,
                CommandType.DELETE_PACKAGE_TYPE, deletePackageTypeCommandFactor,
                CommandType.EDIT_PACKAGE_TYPE, editPackageTypeCommandFactory,
                CommandType.EXIT, exitCommandFactory);
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
