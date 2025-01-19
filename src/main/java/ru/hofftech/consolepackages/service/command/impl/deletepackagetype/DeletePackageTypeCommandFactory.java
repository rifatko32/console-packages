package ru.hofftech.consolepackages.service.command.impl.deletepackagetype;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandParser;

/**
 * The class implements the factory of commands for deleting a package type.
 */
@RequiredArgsConstructor
public class DeletePackageTypeCommandFactory implements CommandAbstractFactory {
    private static final String NAME = "name";

    private final PackageTypeRepository packageTypeRepository;

    /**
     * Creates the command to delete a package type.
     *
     * @param commandContext the context of the command
     * @return the command to delete a package type
     */
    @Override
    public Command createCommand(CommandContext commandContext) {
        return new DeletePackageTypeCommand((DeletePackageTypeContext) commandContext, packageTypeRepository);
    }

    /**
     * Creates the context of the command to delete a package type.
     *
     * @param strCommand the string containing the command
     * @return the context of the command to delete a package type
     * @throws IllegalArgumentException if the name is empty
     */
    @Override
    public CommandContext createCommandContext(String strCommand) {
        var commandKeyValues = CommandParser.parseCommandKeys(strCommand);

        var name = commandKeyValues.get(NAME);

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is empty");
        }

        return new DeletePackageTypeContext(name);
    }

    /**
     * Creates the context of the command to find a package type by name.
     *
     * @param name the name of the package type to find
     * @return the context of the command to find a package type by name
     */
    public CommandContext createCommandContextByParameters(String name) {
        return new DeletePackageTypeContext(name);
    }
}
