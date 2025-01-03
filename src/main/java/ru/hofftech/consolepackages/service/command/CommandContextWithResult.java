package ru.hofftech.consolepackages.service.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CommandContextWithResult<T> implements CommandContext {
    private T result;
}
