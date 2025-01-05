package ru.hofftech.consolepackages.service.command.impl.placepackage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.PackageFromFilePlaceService;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;
import ru.hofftech.consolepackages.service.truck.TruckFactory;

@Slf4j
@RequiredArgsConstructor
public class PlacePackagesCommand implements Command {

    private final PackageFromFilePlaceService packagePlaceService;
    private final ReportWriterFactory reportWriterFactory;
    private final PlacePackageContext context;

    @Override
    public void execute() {
        log.info("Start of handling file: {}", context.filePath());

        var packagePlaceReport = packagePlaceService.placePackages(
                context.filePath(),
                context.algorithmType(),
                context.reportEngineType(),
                TruckFactory.createTrucks(context.trucks()));

        var reportWriter = reportWriterFactory.createReportWriter(context.reportOutputChannelType(), context.filePath());
        reportWriter.writeReport(packagePlaceReport);

        log.info("End of handling file: {}", context.filePath());
    }
}
