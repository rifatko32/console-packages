package ru.hofftech.consolepackages.service.command;

import java.util.regex.Pattern;

/**
 * Contains constants for commands, their parameters and regular expressions for parsing commands and their parameters.
 */
public class CommandConstants {
    public static final String EXIT_COMMAND_PATTERN = "exit";
    public static final String LOAD_COMMAND_PATTERN = "load";
    public static final String UNLOAD_COMMAND_PATTERN = "unload";
    public static final String CREATE_COMMAND_PATTERN = "create";
    public static final String FIND_COMMAND_PATTERN = "find";
    public static final String DELETE_COMMAND_PATTERN = "delete";
    public static final String EDIT_COMMAND_PATTERN = "edit";

    public static final Pattern COMMAND_KEYS_PATTERN = Pattern.compile("--(\\S+)(?:\\s+\"([^\"]*)\"|\\s+(\\S+))?");
}
