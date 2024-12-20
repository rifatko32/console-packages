package ru.hofftech.consolepackages.service.packageitem.engine.impl;

import ru.hofftech.consolepackages.service.packageitem.Package;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithm;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.ArrayList;
import java.util.List;

public class SinglePackagePerTruckPlaceAlgorithm implements PackagePlaceAlgorithm {
    private final int TRUCK_BACK_WIDTH = 6;
    private final int TRUCK_BACK_HEIGHT = 6;

    @Override
    public List<Truck> placePackages(List<ru.hofftech.consolepackages.service.packageitem.Package> packages) {

        if (packages.isEmpty()) {
            return new ArrayList<>();
        }

        return placePackageRecords(packages);
    }

    private List<Truck> placePackageRecords(List<ru.hofftech.consolepackages.service.packageitem.Package> packages) {
        var trucks = new ArrayList<Truck>();

        for (var packageRecord : packages) {
            var truck = new Truck(TRUCK_BACK_WIDTH, TRUCK_BACK_HEIGHT);
            tryPlacePackage(packageRecord, truck);
            trucks.add(truck);
        }

        return trucks;
    }

    private void tryPlacePackage(Package packageRecord, Truck truck) {
        var fillingSlots = packageRecord.mapToListOfFillingSlots(TRUCK_BACK_WIDTH - 1, TRUCK_BACK_HEIGHT - 1);

        truck.fillBackTruckSlots(fillingSlots, packageRecord.getDescriptionNumber());

        truck.loadPackage(packageRecord);
    }
}
