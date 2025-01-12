package ru.hofftech.consolepackages.service.report.packageitem.impl;

import ru.hofftech.consolepackages.service.report.packageitem.PackagePlaceReportEngine;
import ru.hofftech.consolepackages.service.report.PlaneStringReport;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.ArrayList;
import java.util.List;

public class PackagePlaceStringReportEngine implements PackagePlaceReportEngine {
    private static final String TRUCK_DELIMiTER = "-------------------------------------------";
    private static final String TRUCK_BACK_SIDE = "++++++++";
    private static final String TRUCK_SIDE = "+";

    @Override
    public PlaneStringReport generateReport(List<Truck> trucks) {
        var report = new PlaneStringReport();

        if (trucks.isEmpty()) {
            return report;
        }

        for (Truck truck : trucks) {
            report.addReportString(TRUCK_DELIMiTER);
            var truckStrings = createTruckReportStrings(truck);
            report.addReportStrings(truckStrings);
        }

        return report;
    }

    private List<String> createTruckReportStrings(Truck truck) {
        var stringBuilder = new StringBuilder();
        var trackStrings = new ArrayList<String>();

        for (var y = 0; y <= truck.getHeight() - 1; y++) {
            stringBuilder.setLength(0);
            stringBuilder.append(TRUCK_SIDE);
            for (var x = 0; x <= truck.getWidth() - 1; x++) {
                stringBuilder.append(truck.getBackTruckSlotValue(y, x));
            }
            stringBuilder.append(TRUCK_SIDE);
            trackStrings.add(stringBuilder.toString());
        }

        trackStrings.add(TRUCK_BACK_SIDE);

        return trackStrings;
    }
}
