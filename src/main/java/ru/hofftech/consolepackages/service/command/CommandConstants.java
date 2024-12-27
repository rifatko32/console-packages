package ru.hofftech.consolepackages.service.command;

import java.util.regex.Pattern;

public class CommandConstants {
    public static final Integer DEFAULT_TRUCK_COUNT = 10;
    public static final String EXIT_COMMAND = "exit";
    public static final Pattern PLACE_PACKAGES_FROM_TXT_TO_CONSOLE_COMMAND_PATTERN = Pattern.compile("import_txt_to_console (.+\\.txt) \\d* [a-zA-Z]*$");
    public static final Pattern PLACE_PACKAGES_FROM_TXT_TO_JSON_COMMAND_PATTERN = Pattern.compile("import_txt_to_json (.+\\.txt) \\d* [a-zA-Z]*$");
    public static final Pattern UNLOAD_TRUCKS_FROM_JSON_TO_TXT_COMMAND_PATTERN = Pattern.compile("import_json_trucks_to_txt_packages (.+\\.json)");
    public static final Pattern TRUCK_COUNT_PATTERN = Pattern.compile("\\d+");
    public static final Pattern PLACING_ALGORITHM_NAME_PATTERN = Pattern.compile("[a-zA-Z]+$");
}
