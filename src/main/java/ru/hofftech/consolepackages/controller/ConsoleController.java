package ru.hofftech.consolepackages.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.command.AbstractFactoryProvider;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;

import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {
    private final AbstractFactoryProvider abstractFactoryProvider; //CommandFactory

    public void listen() {
        var scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            var strCommand = scanner.nextLine();

            try {
                var factory = abstractFactoryProvider.returnCommandAbstractFactory(strCommand);

                var context = factory.createCommandContext(strCommand);
                var command = factory.createCommand(context);
                command.execute();
            }
            catch (Exception e) {
                log.error("Error while executing command", e);
            }

        }
    }
}
