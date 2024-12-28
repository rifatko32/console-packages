package ru.hofftech.consolepackages.service.command;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import static ru.hofftech.consolepackages.service.command.CommandConstants.COMMAND_KEYS_PATTERN;

public class CommandParser {
    public static CommandType parseCommandType(String strCommand) {
        if (strCommand.startsWith(CommandConstants.LOAD_COMMAND_PATTERN)) {
            return CommandType.LOAD_PACKAGES;
        }

        if (strCommand.startsWith(CommandConstants.UNLOAD_COMMAND_PATTERN)) {
            return CommandType.UNLOAD_TRUCK;
        }

        if (strCommand.startsWith(CommandConstants.EXIT_COMMAND_PATTERN)) {
            return CommandType.EXIT;
        }

        throw new RuntimeException("Invalid command: " + strCommand);
    }

    public static Map<String, String> parseCommandKeys(String strCommand) {
        Map<String, String> optionsMap = new HashMap<>();

        Matcher matcher = COMMAND_KEYS_PATTERN.matcher(strCommand);
        while (matcher.find()) {
            String key = matcher.group(1);
            String quotedValue = matcher.group(2);
            String simpleValue = matcher.group(3);

            String value = quotedValue != null ? quotedValue : simpleValue;
            optionsMap.put(key, value != null ? value : "");
        }

        return optionsMap;
    }
}