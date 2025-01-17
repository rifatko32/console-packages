package ru.hofftech.consolepackages.controller;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.service.command.CommandReader;

import java.util.Scanner;

/**
 * The ConsoleController class is responsible for listening to console input
 * and processing commands using the CommandReader service.
 */
@RequiredArgsConstructor
public class ConsoleController {
    private final CommandReader commandReader;

    /**
     * Start listening to console
     */
    public void listen() {
        var scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            var strCommand = scanner.nextLine();

            commandReader.readCommand(strCommand);
        }
    }
}
