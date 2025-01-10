package ru.hofftech.consolepackages.service.command.impl.unloadtruck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.truck.TruckToPackagesService;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;

/**
 * Command to unload packages from trucks and generate a report.
 * <p>
 * This command uses the TruckToPackagesService to unload packages from the trucks specified
 * in the context's input file. It then generates a report using the specified report engine
 * type and outputs it through the specified report output channel.
 * </p>
 */
@Slf4j
@RequiredArgsConstructor
public class UnloadTrucksCommand implements Command {
    private final TruckToPackagesService truckToPackagesService;
    private final ReportWriterFactory reportWriterFactory;
    private final UnloadTruckContext context;

    @Override
    public void execute() {
        log.info("Start of handling file: {}", context.inFilePath());

        var report = truckToPackagesService.retrieveTruckPackages(context.inFilePath(), context.reportEngineType());

        var reportWriter = reportWriterFactory.createReportWriter(context.reportOutputChannelType(), context.outFilePath());
        reportWriter.writeReport(report);

        log.info("End of handling file: {}", context.inFilePath());
    }
}
