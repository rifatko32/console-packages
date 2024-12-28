package ru.hofftech.consolepackages.service.command.impl.createpackage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.command.Command;

@Slf4j
@RequiredArgsConstructor
public class CreatePackageCommand implements Command {
    private final CreatePackageContext context;

    @Override
    public void execute() {

    }
}
