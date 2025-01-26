package ru.hofftech.consolepackages.service.command;

import lombok.experimental.UtilityClass;
import ru.hofftech.consolepackages.exception.CommandParseException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import static ru.hofftech.consolepackages.service.command.CommandConstants.COMMAND_KEYS_PATTERN;

/**
 * The class parses commands and their parameters from a string.
 */
@UtilityClass
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

        if (strCommand.startsWith(CommandConstants.CREATE_COMMAND_PATTERN)) {
            return CommandType.CREATE_PACKAGE_TYPE;
        }

        if (strCommand.startsWith(CommandConstants.FIND_COMMAND_PATTERN)) {
            return CommandType.FIND_PACKAGE_TYPE;
        }

        if (strCommand.startsWith(CommandConstants.DELETE_COMMAND_PATTERN)) {
            return CommandType.DELETE_PACKAGE_TYPE;
        }

        if (strCommand.startsWith(CommandConstants.EDIT_COMMAND_PATTERN)) {
            return CommandType.EDIT_PACKAGE_TYPE;
        }

        if (strCommand.startsWith(CommandConstants.BILLING_COMMAND_PATTERN)) {
            return CommandType.USER_BILLING_REPORT;
        }

        throw new CommandParseException("Invalid command: " + strCommand);
    }

    /**
     * Parses command keys from a string.
     * @param strCommand the command string containing keys.
     * @return a map of keys and their values.
     */
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
