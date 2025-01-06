package ru.hofftech.consolepackages.service.report.truck.impl;

import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;
import ru.hofftech.consolepackages.service.report.truck.TruckUnloadingReportEngine;

import java.util.List;

import static ru.hofftech.consolepackages.service.report.truck.TruckConstants.PACKAGE_DELIMITER;

public class TruckUnloadingStringReportEngine implements TruckUnloadingReportEngine {

    @Override
    public PackagePlaceStringReport generateReport(List<ru.hofftech.consolepackages.service.packageitem.Package> packages) {
        var report = new PackagePlaceStringReport();

        for (var i = 0; i < packages.size(); i++){
            report.addReportString(packages.get(i).toString());

            if (i < packages.size() - 1) {
                report.addReportString(PACKAGE_DELIMITER);
            }
        }

        return report;
    }
}
