package ru.hofftech.consolepackages.service.command.impl.editpackagetype;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandParser;

/**
 * The class implements the factory of commands for editing a package type.
 */
@RequiredArgsConstructor
public class EditPackageTypeCommandFactory implements CommandAbstractFactory {

    private static final String NAME = "name";
    private static final String FORM = "form";
    private static final String DESCRIPTION = "description";

    private final PackageTypeRepository packageTypeRepository;

    /**
     * Creates the command to edit a package type.
     *
     * @param commandContext the context of the command
     * @return the command to edit a package type
     */
    @Override
    public Command createCommand(CommandContext commandContext) {
        return new EditPackageTypeCommand((EditPackageTypeContext) commandContext, packageTypeRepository);
    }

    /**
     * Creates the context of the command to edit a package type.
     *
     * @param strCommand the string with the command
     * @return the context of the command to edit a package type
     */
    @Override
    public CommandContext createCommandContext(String strCommand) {
        var commandKeyValues = CommandParser.parseCommandKeys(strCommand);

        var name = commandKeyValues.get(NAME);

        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Name is empty");
        }

        var form = commandKeyValues.get(FORM);
        var description = commandKeyValues.get(DESCRIPTION);

        return new EditPackageTypeContext(name, form, description);
    }
}
