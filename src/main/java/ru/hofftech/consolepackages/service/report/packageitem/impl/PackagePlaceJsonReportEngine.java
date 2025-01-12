package ru.hofftech.consolepackages.service.report.packageitem.impl;

import com.google.gson.GsonBuilder;
import ru.hofftech.consolepackages.service.report.packageitem.PackagePlaceReportEngine;
import ru.hofftech.consolepackages.service.report.PlaneStringReport;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.List;

public class PackagePlaceJsonReportEngine implements PackagePlaceReportEngine {
    @Override
    public PlaneStringReport generateReport(List<Truck> trucks) {
        var report = new PlaneStringReport();

        var gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(trucks);

        report.addReportString(jsonString);

        return report;
    }
}
