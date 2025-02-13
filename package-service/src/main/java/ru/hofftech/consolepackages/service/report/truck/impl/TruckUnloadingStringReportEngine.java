package ru.hofftech.consolepackages.service.report.truck.impl;

import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.service.report.PlaneStringReport;
import ru.hofftech.consolepackages.service.report.truck.TruckUnloadingReportEngine;

import java.util.List;

import static ru.hofftech.consolepackages.service.report.truck.TruckConstants.PACKAGE_DELIMITER;

/**
 * Generates a report of the packages in the form of a string, with each package on a new line
 */
public class TruckUnloadingStringReportEngine implements TruckUnloadingReportEngine {

    /**
     * Generates a report of the packages in the form of a string, with each package on a new line
     *
     * @param packages the list of packages to be included in the report
     * @return a {@link PlaneStringReport} containing the report details
     */
    @Override
    public PlaneStringReport generateReport(List<Package> packages) {
        var report = new PlaneStringReport();

        for (var i = 0; i < packages.size(); i++){
            report.addReportString(packages.get(i).toString());

            if (i < packages.size() - 1) {
                report.addReportString(PACKAGE_DELIMITER);
            }
        }

        return report;
    }
}
