package ru.hofftech.consolepackages.service.command.impl.createpackagetype;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandParser;

import static ru.hofftech.consolepackages.service.command.CommandParametersValidator.validateDescription;
import static ru.hofftech.consolepackages.service.command.CommandParametersValidator.validateForm;
import static ru.hofftech.consolepackages.service.command.CommandParametersValidator.validateId;

/**
 * The class implements the factory of commands for creating a package type.
 */
@RequiredArgsConstructor
public class CreatePackageTypeCommandFactory implements CommandAbstractFactory {

    private static final String NAME = "id";
    private static final String FORM = "form";
    private static final String DESCRIPTION = "description";

    private final PackageTypeRepository packageTypeRepository;

    /**
     * Creates the command to create a new package type.
     *
     * @param commandContext the context of the command
     * @return the command to create a new package type
     */
    @Override
    public Command createCommand(CommandContext commandContext) {
        return new CreatePackageTypeCommand((CreatePackageTypeContext) commandContext, packageTypeRepository);
    }


    /**
     * Creates the context of the command to create a new package type.
     *
     * @param strCommand the string with the command
     * @return the context of the command to create a new package type
     */
    @Override
    public CommandContext createCommandContext(String strCommand) {
        var commandKeyValues = CommandParser.parseCommandKeys(strCommand);

        var id = Long.parseLong(commandKeyValues.get(NAME));
        var form = commandKeyValues.get(FORM);
        var description = commandKeyValues.get(DESCRIPTION);

        validateParameters(id, form, description);

        return CreatePackageTypeContext.builder()
                .id(id)
                .form(form)
                .description(description).build();
    }

    private void validateParameters(Long id, String form, String description) {
        validateId(id);
        validateForm(form);
        validateDescription(description);
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
        return CreatePackageTypeContext.builder()
                .id(id)
                .form(form)
                .description(description)
                .build();
    }
}
