package ru.hofftech.consolepackages.service.report.truck;

import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.service.report.PlaneStringReport;

import java.util.List;

/**
 * Interface for generating reports of packages unloaded from trucks.
 * <p>
 * Implementations of this interface should define how the report is generated
 * based on a list of packages. The report is represented as a
 * {@link PlaneStringReport}.
 * </p>
 */
public interface TruckUnloadingReportEngine {
    /**
     * Generates a report of packages unloaded from trucks.
     * <p>
     * This method takes a list of packages and generates a report detailing
     * the packages as they are unloaded from trucks. The report is encapsulated
     * in a {@link PlaneStringReport} instance.
     * </p>
     *
     * @param packages the list of packages to be included in the report
     * @return a {@link PlaneStringReport} containing the report details
     */
    PlaneStringReport generateReport(List<Package> packages);
}
