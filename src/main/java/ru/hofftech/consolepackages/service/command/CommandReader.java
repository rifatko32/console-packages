package ru.hofftech.consolepackages.service.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The class is responsible for reading and executing commands from a string.
 * It uses the {@link AbstractFactoryProvider} to create a command from a string.
 * The command is then executed and the result of the execution is returned as a string.
 */
@Slf4j
@RequiredArgsConstructor
public class CommandReader {
    private final AbstractFactoryProvider abstractFactoryProvider;

    /**
     * Reads and executes a command from a string.
     *
     * @param strCommand a command to be executed
     * @return the result of the command execution as a string
     */
    public String readCommand(String strCommand) {
        try {
            var factory = abstractFactoryProvider.returnCommandAbstractFactory(strCommand);

            var context = factory.createCommandContext(strCommand);
            var command = factory.createCommand(context);
            command.execute();

            if (context instanceof CommandContextWithResult) {
                return ((CommandContextWithResult<?>) context).getResult().toString();
            }
        } catch (Exception e) {
            log.error("Error while executing command", e);
        }
        return null;
    }
}
