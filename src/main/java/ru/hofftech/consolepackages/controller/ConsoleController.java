package ru.hofftech.consolepackages.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.command.AbstractFactoryProvider;
import ru.hofftech.consolepackages.service.command.CommandContextWithResult;

import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {
    private final AbstractFactoryProvider abstractFactoryProvider;

    public void listen() {
        var scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            var strCommand = scanner.nextLine();

            try {
                var factory = abstractFactoryProvider.returnCommandAbstractFactory(strCommand);

                var context = factory.createCommandContext(strCommand);
                var command = factory.createCommand(context);
                command.execute();

                if (context instanceof CommandContextWithResult) {
                    var result = ((CommandContextWithResult<?>) context).getResult();
                    System.out.println(result.toString());
                }
            }
            catch (Exception e) {
                log.error("Error while executing command", e);
            }

        }
    }
}
