package ru.hofftech.consolepackages.service.command.impl.exit;

import ru.hofftech.consolepackages.service.command.Command;

/**
 * Command to exit from application.
 */
public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.exit(0);
    }
}
