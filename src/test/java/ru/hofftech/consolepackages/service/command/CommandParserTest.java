package ru.hofftech.consolepackages.service.command;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandParserTest {
    @Test
    void testParseCommandType_LoadCommand() {
        String command = "load -packages-file \"packages.txt\" -trucks \"6x6;6x6;6x6\" -type \"width\" -out json-file -out-filename \"trucks.json\"";
        CommandType expectedType = CommandType.LOAD_PACKAGES;
        CommandType actualType = CommandParser.parseCommandType(command);
        assertEquals(expectedType, actualType);
    }

    @Test
    void testParseCommandType_UnloadCommand() {
        String command = "unload -infile \"trucks.json\" -outfile \"packages.txt\"";
        CommandType expectedType = CommandType.UNLOAD_TRUCK;
        CommandType actualType = CommandParser.parseCommandType(command);
        assertEquals(expectedType, actualType);
    }

    @Test
    void testParseCommandType_ExitCommand() {
        String command = "exit";
        CommandType expectedType = CommandType.EXIT;
        CommandType actualType = CommandParser.parseCommandType(command);
        assertEquals(expectedType, actualType);
    }

    @Test
    void testParseCommandType_CreateCommand() {
        String command = "create -name \"type wheel\" -form \"xxx\\nx x\\nxxxx\" -description \"o\"";
        CommandType expectedType = CommandType.CREATE_PACKAGE_TYPE;
        CommandType actualType = CommandParser.parseCommandType(command);
        assertEquals(expectedType, actualType);
    }

    @Test
    void testParseCommandType_FindCommand() {
        String command = "find -name \"type 1\"";
        CommandType expectedType = CommandType.FIND_PACKAGE_TYPE;
        CommandType actualType = CommandParser.parseCommandType(command);
        assertEquals(expectedType, actualType);
    }

    @Test
    void testParseCommandType_DeleteCommand() {
        String command = "delete -name \"type 1\"";
        CommandType expectedType = CommandType.DELETE_PACKAGE_TYPE;
        CommandType actualType = CommandParser.parseCommandType(command);
        assertEquals(expectedType, actualType);
    }

    @Test
    void testParseCommandType_EditCommand() {
        String command = "edit -name \"type 3\" -form \"xxx\\nxxx\\nxxx\" -description \"X\"";
        CommandType expectedType = CommandType.EDIT_PACKAGE_TYPE;
        CommandType actualType = CommandParser.parseCommandType(command);
        assertEquals(expectedType, actualType);
    }

    @Test
    void testParseCommandType_InvalidCommand() {
        String command = "invalid command";
        try {
            CommandParser.parseCommandType(command);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            // expected
        }
    }

    @Test
    void testParseCommandKeys() {
        String command = "load --packages-file \"packages.txt\" --trucks \"6x6;6x6;6x6\" --type \"width\" --out json-file --out-filename \"trucks.json\"";
        Map<String, String> expectedKeys = new HashMap<>();
        expectedKeys.put("packages-file", "packages.txt");
        expectedKeys.put("out-filename", "trucks.json");
        expectedKeys.put("trucks", "6x6;6x6;6x6");
        expectedKeys.put("type", "width");
        expectedKeys.put("out", "json-file");
        Map<String, String> actualKeys = CommandParser.parseCommandKeys(command);
        assertEquals(expectedKeys, actualKeys);
    }
}
