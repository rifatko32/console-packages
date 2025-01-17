package ru.hofftech.consolepackages.service.command.impl.createpackagetype;

import ru.hofftech.consolepackages.service.command.CommandContext;

public record CreatePackageTypeContext(
        String name,
        String description,
        String form) implements CommandContext {
}
