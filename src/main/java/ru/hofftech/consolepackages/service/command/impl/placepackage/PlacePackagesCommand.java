package ru.hofftech.consolepackages.service.command.impl.placepackage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.billing.PackageBillingService;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.service.packageitem.PackageFromFileReader;
import ru.hofftech.consolepackages.service.packageitem.PackageFromStringReader;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmFactory;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;
import ru.hofftech.consolepackages.service.report.packageitem.PackagePlaceReportEngineFactory;
import ru.hofftech.consolepackages.service.truck.TruckFactory;

import java.util.ArrayList;

/**
 * Command to place packages into trucks based on a specified algorithm.
 * <p>
 * This command utilizes various components to read package data, apply a placement algorithm,
 * and generate a report of the package placement.
 * </p>
 */
@Slf4j
@RequiredArgsConstructor
public class PlacePackagesCommand implements Command {

    private final PackageFromFileReader packageFromFileReader;
    private final PackageFromStringReader packageFromStringReader;
    private final ReportWriterFactory reportWriterFactory;
    private final PlacePackageContext context;
    private final PackagePlaceAlgorithmFactory placeEngineFactory;
    private final PackagePlaceReportEngineFactory reportEngineFactory;
    private final PackageBillingService packageBillingService;

    /**
     * Executes the command to place packages into trucks based on a specified algorithm.
     */
    @Override
    public void execute() {
        log.info("Start of handling file: {}", context.getFilePath());

        var trucks = TruckFactory.createTrucks(context.getTrucks());

        var packages = new ArrayList<Package>();

        if (context.getFilePath() != null && !context.getFilePath().isEmpty()) {
            packages = packageFromFileReader.readPackages(context.getFilePath());
        } else if (context.getPackagesText() != null && !context.getPackagesText().isEmpty()) {
            packages = packageFromStringReader.readPackages(context.getPackagesText());
        }

        var packagePlaceEngine = placeEngineFactory.createPackagePlaceEngine(context.getAlgorithmType());
        packagePlaceEngine.placePackages(packages, trucks);

        var reportEngine = reportEngineFactory.createReportEngine(context.getReportEngineType());
        var packagePlaceReport = reportEngine.generateReport(trucks);

        var reportWriter = reportWriterFactory.createReportWriter(context.getReportOutputChannelType(), context.getOutputFileName());

        if (reportWriter != null) {
            reportWriter.writeReport(packagePlaceReport);
        }
        else {
            context.setResult(packagePlaceReport.toPlainString());
        }

        packageBillingService.creatLoadPackageBill(trucks, context.getClientId());

        log.info("End of handling file: {}", context.getFilePath());
    }
}
