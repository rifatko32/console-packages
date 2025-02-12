package ru.hofftech.consolepackages.service.command.impl.editpackagetype;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandParser;

import static ru.hofftech.consolepackages.service.command.CommandParametersValidator.validateFormDescription;
import static ru.hofftech.consolepackages.service.command.CommandParametersValidator.validateId;

/**
 * The class implements the factory of commands for editing a package type.
 */
@RequiredArgsConstructor
public class EditPackageTypeCommandFactory implements CommandAbstractFactory {

    private static final String ID = "id";
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

        var id = Long.parseLong(commandKeyValues.get(ID));
        var form = commandKeyValues.get(FORM);
        var description = commandKeyValues.get(DESCRIPTION);

        validateId(id);
        validateFormDescription(form, description);

        return  EditPackageTypeContext.builder()
                .id(id)
                .form(form)
                .description(description)
                .build();
    }

    /**
     * Creates the context of the command to edit a package type by parameters.
     *
     * @param id          the id of the package type
     * @param form        the form of the package type
     * @param description the description of the package type
     * @return the context of the command to edit a package type
     */
    public CommandContext createCommandContextByParameters(Long id, String form, String description) {

        validateFormDescription(form, description);

        return EditPackageTypeContext.builder()
                .id(id)
                .form(form)
                .description(description)
                .build();
    }
}
