package ru.hofftech.consolepackages.service.report.packageitem;

import ru.hofftech.consolepackages.service.report.PlaneStringReport;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.List;

public interface PackagePlaceReportEngine {
    PlaneStringReport generateReport(List<Truck> trucks);
}
