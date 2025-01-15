package ru.hofftech.consolepackages.service.report.packageitem;

import ru.hofftech.consolepackages.service.report.PlaneStringReport;
import ru.hofftech.consolepackages.model.Truck;

import java.util.List;

/**
 * Interface for generating reports of packages in trucks.
 * <p>
 * Implementations of this interface should define how the report is generated
 * based on a list of trucks. The report is represented as a
 * {@link PlaneStringReport}.
 * </p>
 */
public interface PackagePlaceReportEngine {
    /**
     * Generates a report of packages in trucks.
     * <p>
     * This method takes a list of trucks and generates a report detailing
     * the packages as they are placed in trucks. The report is encapsulated
     * in a {@link PlaneStringReport} instance.
     * </p>
     *
     * @param trucks the list of trucks to be included in the report
     * @return a {@link PlaneStringReport} containing the report details
     */
    PlaneStringReport generateReport(List<Truck> trucks);
}
