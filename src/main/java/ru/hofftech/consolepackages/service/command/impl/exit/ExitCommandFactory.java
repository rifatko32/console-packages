package ru.hofftech.consolepackages.service.command.impl.exit;

import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;

public class ExitCommandFactory implements CommandAbstractFactory {
    @Override
    public Command createCommand(CommandContext commandContext) {
        return new ExitCommand();
    }

    @Override
    public CommandContext createCommandContext(String strCommand) {
        return null;
    }
}
