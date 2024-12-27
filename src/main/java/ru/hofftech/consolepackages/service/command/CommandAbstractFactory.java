package ru.hofftech.consolepackages.service.command;

public interface CommandAbstractFactory {
    Command createCommand(CommandContext commandContext);
    CommandContext createCommandContext(String strCommand);
}
