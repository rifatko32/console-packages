package ru.hofftech.consolepackages.service.command.impl.createpackagetype;

import lombok.Builder;
import ru.hofftech.consolepackages.service.command.CommandContext;

@Builder
public record CreatePackageTypeContext(
        Long id,
        String description,
        String form) implements CommandContext {
}
