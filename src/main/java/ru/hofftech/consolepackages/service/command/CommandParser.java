package ru.hofftech.consolepackages.service.command;

import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmType;

import java.util.regex.Matcher;

public class CommandParser {
    public static CommandType parseCommandType(String strCommand) {
        var matcher = CommandConstants.PLACE_PACKAGES_FROM_TXT_TO_CONSOLE_COMMAND_PATTERN.matcher(strCommand);
        if (matcher.matches()) {
            return CommandType.PLACE_PACKAGES_FROM_TXT_FILE_TO_CONSOLE;
        }

        matcher = CommandConstants.PLACE_PACKAGES_FROM_TXT_TO_JSON_COMMAND_PATTERN.matcher(strCommand);
        if (matcher.matches()) {
            return CommandType.PLACE_PACKAGES_FROM_TXT_FILE_TO_JSON_FILE;
        }

        matcher = CommandConstants.UNLOAD_TRUCKS_FROM_JSON_TO_TXT_COMMAND_PATTERN.matcher(strCommand);
        if (matcher.matches()) {
            return CommandType.UNLOAD_TRUCKS_FROM_JSON_TO_TXT_FILE;
        }

        if (strCommand.equals(CommandConstants.EXIT_COMMAND)) {
            return CommandType.EXIT;
        }

        throw new RuntimeException("Invalid command: " + strCommand);
    }

    public static Integer readTruckCount(String strCommand) {
        Matcher truckCountMatcher = CommandConstants.TRUCK_COUNT_PATTERN.matcher(strCommand);
        var truckCount = CommandConstants.DEFAULT_TRUCK_COUNT;
        if (truckCountMatcher.find()) {
            truckCount = Integer.parseInt(truckCountMatcher.group(0));
        }
        return truckCount;
    }

    public static PackagePlaceAlgorithmType readAlgorithmName(String strCommand) {
        var algorithmName = "";
        var algorithmNameMatcher = CommandConstants.PLACING_ALGORITHM_NAME_PATTERN.matcher(strCommand);
        if (algorithmNameMatcher.find()) {
            algorithmName = algorithmNameMatcher.group(0);
        }

        return switch (algorithmName) {
            case CommandConstants.EQUAL_ALGORITHM_NAME -> PackagePlaceAlgorithmType.EQUAL_DISTRIBUTION;
            case CommandConstants.SINGLE_ALGORITHM_NAME -> PackagePlaceAlgorithmType.SINGLE_PACKAGE_PER_TRUCK;
            case CommandConstants.WIDTH_ALGORITHM_NAME -> PackagePlaceAlgorithmType.PACKAGE_PLACE_BY_WIDTH;
            default -> throw new IllegalStateException("Unexpected algorithm name value: " + algorithmName);
        };
    }

    public static String readFilePath(String strCommand, CommandType commandType) {
        return switch (commandType) {
            case PLACE_PACKAGES_FROM_TXT_FILE_TO_CONSOLE -> returnFilePathMatchGroup(
                    CommandConstants.PLACE_PACKAGES_FROM_TXT_TO_CONSOLE_COMMAND_PATTERN.matcher(strCommand)
            );
            case PLACE_PACKAGES_FROM_TXT_FILE_TO_JSON_FILE -> returnFilePathMatchGroup(
                    CommandConstants.PLACE_PACKAGES_FROM_TXT_TO_JSON_COMMAND_PATTERN.matcher(strCommand)
            );
            case UNLOAD_TRUCKS_FROM_JSON_TO_TXT_FILE -> returnFilePathMatchGroup(
                    CommandConstants.UNLOAD_TRUCKS_FROM_JSON_TO_TXT_COMMAND_PATTERN.matcher(strCommand)
            );
            default -> throw new IllegalStateException("Unexpected file path value: " + commandType);
        };
        }

    private static String returnFilePathMatchGroup(Matcher matcher) {
        if (matcher.matches()) {
            return matcher.group(1);
        }
        throw new RuntimeException("Invalid command: " + matcher.group(0));
    }
}
