package ru.hofftech.consolepackages.service.command.impl.findpackagetype;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandParser;

@RequiredArgsConstructor
public class FindPackageTypeCommandFactory implements CommandAbstractFactory {

    private static final String NAME = "name";
    private final PackageTypeRepository packageTypeRepository;

    @Override
    public Command createCommand(CommandContext commandContext) {
        return new FindPackageTypeCommand((FindPackageTypeContext) commandContext, packageTypeRepository);
    }

    @Override
    public CommandContext createCommandContext(String strCommand) {
        var commandKeyValues = CommandParser.parseCommandKeys(strCommand);

        var name = commandKeyValues.get(NAME);

        return new FindPackageTypeContext(name);
    }
}
