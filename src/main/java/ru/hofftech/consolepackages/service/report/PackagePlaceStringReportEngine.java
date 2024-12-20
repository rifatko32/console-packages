package ru.hofftech.consolepackages.service.report;

import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.ArrayList;
import java.util.List;

public class PackagePlaceStringReportEngine {
    private static final String TRUCK_DELIMiTER = "-------------------------------------------";
    private static final String TRUCK_BACK_SIDE = "++++++++";
    private static final String TRUCK_SIDE = "+";

    public PackagePlaceStringReport generateReport(List<Truck> trucks) {
        var report = new PackagePlaceStringReport();

        if (trucks.isEmpty()) {
            return report;
        }

        for (Truck truck : trucks) {
            report.getReportStrings().add(TRUCK_DELIMiTER);
            var truckStrings = createTruckReportStrings(truck);
            report.getReportStrings().addAll(truckStrings);
        }

        return report;
    }

    private List<String> createTruckReportStrings(Truck truck) {
        var backTruckSlots = truck.getBackTruckSlots();
        var stringBuilder = new StringBuilder();
        var trackStrings = new ArrayList<String>();

        for (var x = 0; x <= truck.getWidth() - 1; x++) {
            stringBuilder.setLength(0);
            stringBuilder.append(TRUCK_SIDE);
            for (var y = 0; y <= truck.getHeight() - 1; y++) {
                stringBuilder.append(backTruckSlots[x][y] == 0 ? " " : backTruckSlots[x][y]);
            }
            stringBuilder.append(TRUCK_SIDE);
            trackStrings.add(stringBuilder.toString());
        }

        trackStrings.add(TRUCK_BACK_SIDE);

        return trackStrings;
    }
}
