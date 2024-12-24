package ru.hofftech.consolepackages.service.report.packageitem;

import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.List;

public interface PackagePlaceReportEngine {
    PackagePlaceStringReport generateReport(List<Truck> trucks);
}
