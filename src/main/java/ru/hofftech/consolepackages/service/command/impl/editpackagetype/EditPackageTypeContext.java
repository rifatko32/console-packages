package ru.hofftech.consolepackages.service.command.impl.editpackagetype;

import lombok.Builder;
import ru.hofftech.consolepackages.service.command.CommandContext;

@Builder
public record EditPackageTypeContext(
        String name,
        String form,
        String description) implements CommandContext {
}
