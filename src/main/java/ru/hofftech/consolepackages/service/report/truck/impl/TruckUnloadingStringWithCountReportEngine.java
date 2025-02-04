package ru.hofftech.consolepackages.service.report.truck.impl;

import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.service.report.PlaneStringReport;
import ru.hofftech.consolepackages.service.report.truck.TruckUnloadingReportEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.hofftech.consolepackages.service.report.truck.TruckConstants.PACKAGE_DELIMITER;

/**
 * Implementation of {@link TruckUnloadingReportEngine} that generates a report
 * of packages unloaded from trucks, including a count of each package type.
 * <p>
 * This report engine groups packages by their type name and counts the number
 * of occurrences of each type. The resulting report consists of strings
 * representing the package type and its count, separated by a delimiter.
 * </p>
 *
 * @see TruckUnloadingReportEngine
 */
public class TruckUnloadingStringWithCountReportEngine implements TruckUnloadingReportEngine {


    /**
     * Generates a report of packages unloaded from trucks, including a count of each package type.
     * <p>
     * This method takes a list of packages and generates a report detailing
     * the packages as they are unloaded from trucks. The report is encapsulated
     * in a {@link PlaneStringReport} instance.
     * </p>
     *
     * @param packages the list of packages to be included in the report
     * @return a {@link PlaneStringReport} containing the report details
     */
    @Override
    public PlaneStringReport generateReport(List<Package> packages) {
        var report = new PlaneStringReport();

        var groupedTypeNames = packages
                .stream()
                .map(Package::getTypeId)
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        var keys = new ArrayList<>(groupedTypeNames.keySet());

        for (int i = 0; i < keys.size(); i++) {
            var packageTypeName = keys.get(i);
            var packageCount = groupedTypeNames.get(packageTypeName);
            report.addReportString(String.format("\"%s\", %d", packageTypeName, packageCount));

            if (i < packages.size() - 1) {
                report.addReportString(PACKAGE_DELIMITER);
            }
        }

        return report;
    }
}
