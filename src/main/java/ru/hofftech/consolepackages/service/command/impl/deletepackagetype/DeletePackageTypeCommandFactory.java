package ru.hofftech.consolepackages.service.command.impl.deletepackagetype;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandParser;

@RequiredArgsConstructor
public class DeletePackageTypeCommandFactory implements CommandAbstractFactory {
    private static final String NAME = "name";

    private final PackageTypeRepository packageTypeRepository;

    @Override
    public Command createCommand(CommandContext commandContext) {
        return new DeletePackageTypeCommand((DeletePackageTypeContext) commandContext, packageTypeRepository);
    }

    @Override
    public CommandContext createCommandContext(String strCommand) {
        var commandKeyValues = CommandParser.parseCommandKeys(strCommand);

        var name = commandKeyValues.get(NAME);

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is empty");
        }

        return new DeletePackageTypeContext(name);
    }
}
