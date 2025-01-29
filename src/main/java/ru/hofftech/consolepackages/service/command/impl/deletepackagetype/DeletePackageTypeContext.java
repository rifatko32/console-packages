package ru.hofftech.consolepackages.service.command.impl.deletepackagetype;

import ru.hofftech.consolepackages.service.command.CommandContext;

public record DeletePackageTypeContext(Long id) implements CommandContext {
}
