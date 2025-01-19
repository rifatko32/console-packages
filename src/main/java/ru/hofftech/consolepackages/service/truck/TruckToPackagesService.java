package ru.hofftech.consolepackages.service.truck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.service.report.PlaneStringReport;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.truck.TruckUnloadingReportEngineFactory;
import ru.hofftech.consolepackages.util.TruckJsonFileReader;

import java.util.List;

/**
 * Service to get packages from trucks.
 *
 * <p>
 * This service reads a list of trucks from a JSON file and applies a truck unloading algorithm
 * to unload packages from the trucks. It then generates a report of the unloaded packages based
 * on the specified report engine type.
 * </p>
 */
@Slf4j
@RequiredArgsConstructor
public class TruckToPackagesService {
    private final TruckUnloadingReportEngineFactory reportEngineFactory;
    private final TruckUnloadingAlgorithm truckUnloadingAlgorithm;


    /**
     * Retrieves packages from trucks specified in the JSON file and generates a report.
     *
     * <p>
     * This method reads a list of trucks from the provided file path, applies an unloading
     * algorithm to retrieve the packages, and generates a report based on the specified
     * report engine type.
     * </p>
     *
     * @param trucks         the trucks
     * @param reportEngineType the type of report engine to use for generating the report
     * @return a {@link PlaneStringReport} containing the details of the unloaded packages
     */
    public PlaneStringReport retrieveTruckPackages(
            List<Truck> trucks,
            ReportEngineType reportEngineType) {
        try {
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
