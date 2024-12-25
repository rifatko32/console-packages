package ru.hofftech.consolepackages.service.command.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.TruckToPackagesService;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandParser;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;

@Slf4j
@RequiredArgsConstructor
public class UnloadTrucksFromJsonToTxtFileCommand implements Command {
    private final TruckToPackagesService truckToPackagesService;
    private final ReportWriterFactory reportWriterFactory;
    private final String strCommand;

    @Override
    public void execute() {
        var filePath = CommandParser.readFilePath(strCommand, CommandParser.parseCommandType(strCommand));;
        log.info("Start of handling file: {}", filePath);

        var report = truckToPackagesService.getTruckPackages(filePath, ReportEngineType.STRING);

        var reportWriter = reportWriterFactory.createReportWriter(ReportOutputChannelType.TXT_FILE, ".txt");
        reportWriter.writeReport(report);

        log.info("End of handling file: {}", filePath);
    }
}
