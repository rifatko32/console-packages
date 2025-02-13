package ru.hofftech.consolepackages.service.command.impl.exit;

import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;

/**
 * The class implements the factory of commands for exiting from the application.
 */
public class ExitCommandFactory implements CommandAbstractFactory {

    /**
     * Creates a command for exiting from the application.
     *
     * @param commandContext Context of the command.
     * @return A command for exiting from the application.
     */
    @Override
    public Command createCommand(CommandContext commandContext) {
        return new ExitCommand();
    }


    /**
     * Creates the context of the command to exit from the application.
     *
     * @param strCommand the string with the command
     * @return the context of the command to exit from the application
     */
    @Override
    public CommandContext createCommandContext(String strCommand) {
        return null;
    }
}
