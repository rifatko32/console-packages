package ru.hofftech.consolepackages.service.command;

import java.text.ParseException;

/**
 * A factory interface for creating commands and their contexts.
 * <p>
 * Implementations of this interface provide methods to create specific command instances
 * and their associated contexts based on a given command context or string input.
 * </p>
 */
public interface CommandAbstractFactory {
    Command createCommand(CommandContext commandContext);
    CommandContext createCommandContext(String strCommand) throws ParseException;
}
