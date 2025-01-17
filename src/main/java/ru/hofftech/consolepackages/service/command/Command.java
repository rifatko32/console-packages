package ru.hofftech.consolepackages.service.command;

/**
 * Represents a command that can be executed.
 * <p>
 * Implementations of this interface define specific actions to be performed
 * when the {@code execute} method is called.
 * </p>
 */
public interface Command {
    void execute();
}
