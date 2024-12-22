package ru.hofftech.consolepackages.service.report.impl;

import com.google.gson.GsonBuilder;
import ru.hofftech.consolepackages.service.report.PackagePlaceReportEngine;
import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.List;

public class PackagePlaceJsonReportEngine implements PackagePlaceReportEngine {
    @Override
    public PackagePlaceStringReport generateReport(List<Truck> trucks) {
        var report = new PackagePlaceStringReport();

        var gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(trucks);

        report.addReportString(jsonString);

        return report;
    }
}
