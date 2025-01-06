package ru.hofftech.consolepackages.service.truck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.truck.TruckUnloadingReportEngineFactory;
import ru.hofftech.consolepackages.util.TruckJsonFileReader;

@Slf4j
@RequiredArgsConstructor
public class TruckToPackagesService {
    private final TruckJsonFileReader fileReader;
    private final TruckUnloadingReportEngineFactory reportEngineFactory;
    private final TruckUnloadingAlgorithm truckUnloadingAlgorithm;

    public PackagePlaceStringReport getTruckPackages(
            String filePath,
            ReportEngineType reportEngineType) {
        try {
            var trucks = fileReader.readTrucks(filePath);

            if (trucks.isEmpty()) {
                log.info("No packages found");
                return null;
            }

            var packages = truckUnloadingAlgorithm.unloadTruck(trucks);

            var reportEngine = reportEngineFactory.createReportEngine(reportEngineType);
            return reportEngine.generateReport(packages);
        } catch (Exception e) {
            log.error("Error while try to place packages", e);
        }
        return null;
    }
}
