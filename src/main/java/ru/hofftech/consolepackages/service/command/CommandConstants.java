package ru.hofftech.consolepackages.service.command;

import java.util.regex.Pattern;

public class CommandConstants {
    public static final String EXIT_COMMAND_PATTERN = "exit";
    public static final String LOAD_COMMAND_PATTERN = "load";
    public static final String UNLOAD_COMMAND_PATTERN = "unload";

    public static final Pattern COMMAND_KEYS_PATTERN = Pattern.compile("-(\\S+)(?:\\s+\"([^\"]*)\"|\\s+(\\S+))?");
}
