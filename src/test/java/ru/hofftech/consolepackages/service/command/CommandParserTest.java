package ru.hofftech.consolepackages.service.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandParserTest {
    @Test
    public void testParsePlacePackagesFromTxtToConsoleCommand() {
        String strCommand = "import_txt_to_console packages.txt 10 single";
        CommandType expectedCommandType = CommandType.LOAD_PACKAGES;
        CommandType actualCommandType = CommandParser.parseCommandType(strCommand);
        assertEquals(expectedCommandType, actualCommandType);
    }

    @Test
    public void testParsePlacePackagesFromTxtToJsonFileCommand() {
        String strCommand = "import_txt_to_json packages.txt 10 equal";
        CommandType expectedCommandType = CommandType.LOAD_PACKAGES;
        CommandType actualCommandType = CommandParser.parseCommandType(strCommand);
        assertEquals(expectedCommandType, actualCommandType);
    }

    @Test
    public void testParseUnloadTrucksFromJsonToTxtFileCommand() {
        String strCommand = "import_json_trucks_to_txt_packages trucks.json";
        CommandType expectedCommandType = CommandType.UNLOAD_TRUCK;
        CommandType actualCommandType = CommandParser.parseCommandType(strCommand);
        assertEquals(expectedCommandType, actualCommandType);
    }

    @Test
    public void testParseExitCommand() {
        String strCommand = "exit";
        CommandType expectedCommandType = CommandType.EXIT;
        CommandType actualCommandType = CommandParser.parseCommandType(strCommand);
        assertEquals(expectedCommandType, actualCommandType);
    }

    @Test
    public void testParseInvalidCommand() {
        String strCommand = "invalid command";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> CommandParser.parseCommandType(strCommand));
        assertEquals("Invalid command: invalid command", exception.getMessage());
    }

   /* @Test
    public void testReadFilePath_PlacePackagesToConsole() {
        String strCommand = "import_txt_to_console packages.txt 10 single";
        CommandType commandType = CommandType.LOAD_PACKAGES;
        String expectedFilePath = "packages.txt";
        String actualFilePath = CommandParser.parseCommandKeys(strCommand).get();
        assertEquals(expectedFilePath, actualFilePath);
    }

    @Test
    public void testReadFilePath_PlacePackagesToJson() {
        String strCommand = "import_txt_to_json packages.txt 10 equal";
        CommandType commandType = CommandType.LOAD_PACKAGES;
        String expectedFilePath = "packages.txt";
        String actualFilePath = CommandParser.readFilePath(strCommand, commandType);
        assertEquals(expectedFilePath, actualFilePath);
    }
    @Test

    public void testReadFilePath_UnloadTrucksToJson() {
        String strCommand = "import_json_trucks_to_txt_packages trucks.json";
        CommandType commandType = CommandType.UNLOAD_TRUCK;
        String expectedFilePath = "trucks.json";
        String actualFilePath = CommandParser.readFilePath(strCommand, commandType);
        assertEquals(expectedFilePath, actualFilePath);
    }

    @Test
    public void testReadFilePath_InvalidCommandType() {
        String strCommand = "invalid_command";
        CommandType commandType = CommandType.EXIT;
        assertThrows(IllegalStateException.class, () -> CommandParser.readFilePath(strCommand, commandType));
    }

    @Test
    public void testReadFilePath_NullInput() {
        String strCommand = null;
        CommandType commandType = CommandType.LOAD_PACKAGES;
        assertThrows(NullPointerException.class, () -> CommandParser.readFilePath(strCommand, commandType));
    }

    @Test
    public void testReadFilePath_EmptyInput() {
        String strCommand = "";
        CommandType commandType = CommandType.LOAD_PACKAGES;
        assertThrows(RuntimeException.class, () -> CommandParser.readFilePath(strCommand, commandType));
    }

    @Test
    public void testDefaultTruckCountWhenNoMatchIsFound() {
        String strCommand = "import_txt_to_console packages.txt single";
        int expectedTruckCount = CommandConstants.DEFAULT_TRUCK_COUNT;
        int actualTruckCount = CommandParser.readTruckCount(strCommand);
        assertEquals(expectedTruckCount, actualTruckCount);
    }

    @Test
    public void testTruckCountIsParsedCorrectlyWhenMatchIsFound() {
        String strCommand = "import_txt_to_console packages.txt 20 single";
        int expectedTruckCount = 20;
        int actualTruckCount = CommandParser.readTruckCount(strCommand);
        assertEquals(expectedTruckCount, actualTruckCount);
    }

    @Test
    public void testTruckCountIsParsedCorrectlyWhenMatchIsFoundAtTheBeginningOfTheString() {
        String strCommand = "20 import_txt_to_console packages.txt single";
        int expectedTruckCount = 20;
        int actualTruckCount = CommandParser.readTruckCount(strCommand);
        assertEquals(expectedTruckCount, actualTruckCount);
    }

    @Test
    public void testTruckCountIsParsedCorrectlyWhenMatchIsFoundAtTheEndOfTheString() {
        String strCommand = "import_txt_to_console packages.txt single 20";
        int expectedTruckCount = 20;
        int actualTruckCount = CommandParser.readTruckCount(strCommand);
        assertEquals(expectedTruckCount, actualTruckCount);
    }

    @Test
    public void testTruckCountIsParsedCorrectlyWhenMatchIsFoundInTheMiddleOfTheString() {
        String strCommand = "import_txt_to_console 20 packages.txt single";
        int expectedTruckCount = 20;
        int actualTruckCount = CommandParser.readTruckCount(strCommand);
        assertEquals(expectedTruckCount, actualTruckCount);
    }*/
}
