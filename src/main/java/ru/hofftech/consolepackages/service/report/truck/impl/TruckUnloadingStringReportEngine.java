package ru.hofftech.consolepackages.service.report.truck.impl;

import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;
import ru.hofftech.consolepackages.service.report.truck.TruckUnloadingReportEngine;

import java.util.List;

public class TruckUnloadingStringReportEngine implements TruckUnloadingReportEngine {
    private final static String PACKAGE_DELIMITER = System.lineSeparator();

    @Override
    public PackagePlaceStringReport generateReport(List<ru.hofftech.consolepackages.service.packageitem.Package> packages) {
        var report = new PackagePlaceStringReport();

        if (packages.isEmpty()) {
            return report;
        }

        for (ru.hofftech.consolepackages.service.packageitem.Package packageItem : packages) {
            report.addReportString(packageItem.toString());
            report.addReportString(PACKAGE_DELIMITER);
            report.addReportString(PACKAGE_DELIMITER);
        }

        return report;
    }
}
