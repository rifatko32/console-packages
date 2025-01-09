package ru.hofftech.consolepackages.service.command.impl.placepackage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.packageitem.Package;
import ru.hofftech.consolepackages.service.packageitem.PackageFromFileReader;
import ru.hofftech.consolepackages.service.packageitem.PackageFromStringReader;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmFactory;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;
import ru.hofftech.consolepackages.service.report.packageitem.PackagePlaceReportEngineFactory;
import ru.hofftech.consolepackages.service.truck.TruckFactory;

import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
public class PlacePackagesCommand implements Command {

    private final PackageFromFileReader packageFromFileReader;
    private final PackageFromStringReader packageFromStringReader;
    private final ReportWriterFactory reportWriterFactory;
    private final PlacePackageContext context;
    private final PackagePlaceAlgorithmFactory placeEngineFactory;
    private final PackagePlaceReportEngineFactory reportEngineFactory;

    @Override
    public void execute() {
        log.info("Start of handling file: {}", context.filePath());

        var trucks = TruckFactory.createTrucks(context.trucks());

        var packages = new ArrayList<Package>();

        if (context.filePath() != null && !context.filePath().isEmpty()) {
            packages = packageFromFileReader.readPackages(context.filePath());
        } else if (context.packagesText() != null && !context.packagesText().isEmpty()) {
            packages = packageFromStringReader.readPackages(context.packagesText());
        }

        var packagePlaceEngine = placeEngineFactory.createPackagePlaceEngine(context.algorithmType());
        packagePlaceEngine.placePackages(packages, trucks);

        var reportEngine = reportEngineFactory.createReportEngine(context.reportEngineType());
        var packagePlaceReport = reportEngine.generateReport(trucks);

        var reportWriter = reportWriterFactory.createReportWriter(context.reportOutputChannelType(), context.outputFileName());
        reportWriter.writeReport(packagePlaceReport);

        log.info("End of handling file: {}", context.filePath());
    }
}
