package ru.hofftech.consolepackages.service.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CommandReader {
    private final AbstractFactoryProvider abstractFactoryProvider;

    public String readCommand(String strCommand) {
        try {
            var factory = abstractFactoryProvider.returnCommandAbstractFactory(strCommand);

            var context = factory.createCommandContext(strCommand);
            var command = factory.createCommand(context);
            command.execute();

            if (context instanceof CommandContextWithResult) {
                return ((CommandContextWithResult<?>) context).getResult().toString();
            }
        } catch (Exception e) {
            log.error("Error while executing command", e);
        }
        return null;
    }
}
