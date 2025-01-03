package ru.hofftech.consolepackages.service.command.impl.editpackagetype;

import ru.hofftech.consolepackages.service.command.CommandContext;

public record EditPackageTypeContext(
        String name,
        String form,
        String description) implements CommandContext {
}
