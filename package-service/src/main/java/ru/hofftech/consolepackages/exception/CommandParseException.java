package ru.hofftech.consolepackages.exception;

import ru.hofftech.consolepackages.service.command.CommandParser;

/**
 * Exception thrown by the {@link CommandParser}.
 * <p>
 * This exception is thrown if the command line arguments are incorrect.
 * </p>
 */
public class  CommandParseException extends RuntimeException{

    public CommandParseException(String message) {
        super(message);
    }
}
