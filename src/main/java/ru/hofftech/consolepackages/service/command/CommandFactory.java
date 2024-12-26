package ru.hofftech.consolepackages.service.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.PackageFromFilePlaceService;
import ru.hofftech.consolepackages.service.TruckToPackagesService;
import ru.hofftech.consolepackages.service.command.impl.ExitCommand;
import ru.hofftech.consolepackages.service.command.impl.PlacePackagesFromTxtFileToConsoleCommand;
import ru.hofftech.consolepackages.service.command.impl.PlacePackagesFromTxtFileToJsonFileCommand;
import ru.hofftech.consolepackages.service.command.impl.UnloadTrucksFromJsonToTxtFileCommand;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;

@Slf4j
@RequiredArgsConstructor
public class CommandFactory {
    private final PackageFromFilePlaceService packagePlaceService;
    private final TruckToPackagesService truckToPackagesService;
    private final ReportWriterFactory reportWriterFactory;

    public Command createCommand(String strCommand) {

        var commandType = CommandParser.parseCommandType(strCommand);

        return switch (commandType) {
            case PLACE_PACKAGES_FROM_TXT_FILE_TO_CONSOLE ->
                    new PlacePackagesFromTxtFileToConsoleCommand(packagePlaceService, reportWriterFactory, strCommand);
            case PLACE_PACKAGES_FROM_TXT_FILE_TO_JSON_FILE ->
                    new PlacePackagesFromTxtFileToJsonFileCommand(packagePlaceService, reportWriterFactory, strCommand);
            case UNLOAD_TRUCKS_FROM_JSON_TO_TXT_FILE ->
                    new UnloadTrucksFromJsonToTxtFileCommand(truckToPackagesService, reportWriterFactory, strCommand);
            case EXIT -> new ExitCommand();
        };

    }
}
