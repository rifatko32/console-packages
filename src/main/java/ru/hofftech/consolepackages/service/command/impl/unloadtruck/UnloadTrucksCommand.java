package ru.hofftech.consolepackages.service.command.impl.unloadtruck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.datastorage.model.entity.OperationType;
import ru.hofftech.consolepackages.service.billing.PackageBillingService;
import ru.hofftech.consolepackages.service.truck.TruckToPackagesService;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;
import ru.hofftech.consolepackages.util.TruckJsonFileReader;

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
    private final TruckJsonFileReader fileReader;
    private final PackageBillingService packageBillingService;

    /**
     * Executes the command to unload packages from trucks and generate a report of the unloaded packages.
     *
     * @see Command#execute()
     */
    @Override
    public void execute() {
        log.info("Start of handling file: {}", context.inFilePath());

        var trucks = fileReader.readTrucks(context.inFilePath());

        var report = truckToPackagesService.retrieveTruckPackages(trucks, context.reportEngineType());

        var reportWriter = reportWriterFactory.createReportWriter(context.reportOutputChannelType(), context.outFilePath());
        reportWriter.writeReport(report);

        packageBillingService.creatPackageBill(trucks, context.clientId(), OperationType.UNLOAD);

        log.info("End of handling file: {}", context.inFilePath());
    }
}
