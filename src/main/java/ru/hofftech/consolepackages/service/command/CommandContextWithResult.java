package ru.hofftech.consolepackages.service.command;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract class for command context with result.
 * <p>
 * This class extends {@link CommandContext} and adds a result object.
 * </p>
 */
@Getter
@Setter
public abstract class CommandContextWithResult<T> implements CommandContext {
    private T result;
}
