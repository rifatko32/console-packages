package ru.hofftech.consolepackages.service.report.truck;

import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;

import java.util.List;

public interface TruckUnloadingReportEngine {
    PackagePlaceStringReport generateReport(List<ru.hofftech.consolepackages.service.packageitem.Package> packages);
}
