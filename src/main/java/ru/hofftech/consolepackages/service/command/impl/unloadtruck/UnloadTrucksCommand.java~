package ru.hofftech.consolepackages.service.command.impl.unloadtruck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.TruckToPackagesService;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;

@Slf4j
@RequiredArgsConstructor
public class UnloadTrucksCommand implements Command {
    private final TruckToPackagesService truckToPackagesService;
    private final ReportWriterFactory reportWriterFactory;
    private final UnloadTruckContext context;

    @Override
    public void execute() {
        log.info("Start of handling file: {}", context.filePath());

        var report = truckToPackagesService.getTruckPackages(context.filePath(), context.reportEngineType());

        var reportWriter = reportWriterFactory.createReportWriter(context.reportOutputChannelType(), context.fileExtension());
        reportWriter.writeReport(report);

        log.info("End of handling file: {}", context.filePath());
    }
}
