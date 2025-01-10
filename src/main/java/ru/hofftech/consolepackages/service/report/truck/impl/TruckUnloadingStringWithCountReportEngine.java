package ru.hofftech.consolepackages.service.report.truck.impl;

import ru.hofftech.consolepackages.service.packageitem.Package;
import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;
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

    @Override
    public PackagePlaceStringReport generateReport(List<Package> packages) {
        var report = new PackagePlaceStringReport();

        var groupedTypeNames = packages
                .stream()
                .map(Package::getTypeName)
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
