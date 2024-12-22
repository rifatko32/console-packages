package ru.hofftech.consolepackages.service.report;

import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.List;

public interface PackagePlaceReportEngine {
    PackagePlaceStringReport generateReport(List<Truck> trucks);
}
