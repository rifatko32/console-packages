package ru.hofftech.consolepackages.service.command.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.PackageFromFilePlaceService;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandParser;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;

@Slf4j
@RequiredArgsConstructor
public class PlacePackagesFromTxtFileToJsonFileCommand implements Command {
    private final PackageFromFilePlaceService packagePlaceService;
    private final ReportWriterFactory reportWriterFactory;
    private final String strCommand;

    @Override
    public void execute() {
        var truckCount = CommandParser.readTruckCount(strCommand);
        var algorithmType = CommandParser.readAlgorithmName(strCommand);
        var filePath = CommandParser.readFilePath(strCommand, CommandParser.parseCommandType(strCommand));

        log.info("Start of handling file: {}", filePath);

        var packagePlaceReport = packagePlaceService.placePackages(
                filePath,
                algorithmType,
                ReportEngineType.STRING,
                truckCount);

        var reportWriter = reportWriterFactory.createReportWriter(ReportOutputChannelType.JSONFILE, ".json");
        reportWriter.writeReport(packagePlaceReport);

        log.info("End of handling file: {}", filePath);
    }
}
