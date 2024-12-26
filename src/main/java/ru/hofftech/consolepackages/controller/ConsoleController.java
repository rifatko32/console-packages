package ru.hofftech.consolepackages.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.command.CommandFactory;

import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {
    private final CommandFactory commandFactory; //CommandFactory

    public void listen() {
        var scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            var strCommand = scanner.nextLine();

            try {
                var command = commandFactory.createCommand(strCommand);
                command.execute();
            }
            catch (Exception e) {
                log.error("Error while executing command", e);
            }

        }
    }
}
