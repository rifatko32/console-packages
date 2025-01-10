package ru.hofftech.consolepackages.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.command.AbstractFactoryProvider;
import ru.hofftech.consolepackages.service.command.CommandContextWithResult;

import java.util.Scanner;

/**
 * Controller that handles console input and delegates command execution.
 * <p>
 * This controller listens to console input, parses the input to determine
 * the appropriate command, and executes the command. Upon command execution,
 * if the context contains a result, it outputs the result to the console.
 * It also logs any errors encountered during command execution.
 * </p>
 */
@Slf4j
@RequiredArgsConstructor
public class ConsoleController {
    private final AbstractFactoryProvider abstractFactoryProvider;

    /**
     * Start listening to console
     */
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
