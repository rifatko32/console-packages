package ru.hofftech.consolepackages.service.packageitem.engine.impl;

import ru.hofftech.consolepackages.service.packageitem.Package;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithm;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.ArrayList;
import java.util.List;

import static ru.hofftech.consolepackages.service.report.truck.TruckConstants.TRUCK_BACK_HEIGHT;
import static ru.hofftech.consolepackages.service.report.truck.TruckConstants.TRUCK_BACK_WIDTH;

public class SinglePackagePerTruckPlaceAlgorithm implements PackagePlaceAlgorithm {

    @Override
    public List<Truck> placePackages(List<ru.hofftech.consolepackages.service.packageitem.Package> packages, Integer availableTruckCount) {

        if (packages.isEmpty()) {
            return new ArrayList<>();
        }

        return placePackageRecords(packages, availableTruckCount);
    }

    private List<Truck> placePackageRecords(List<ru.hofftech.consolepackages.service.packageitem.Package> packages, Integer availableTruckCount) {
        var trucks = new ArrayList<Truck>();

        for (var packageRecord : packages) {
            checkIsCurrentTruckCountLessThenAvailable(availableTruckCount, trucks);

            var truck = new Truck(TRUCK_BACK_WIDTH, TRUCK_BACK_HEIGHT);
            tryPlacePackage(packageRecord, truck);
            trucks.add(truck);
        }

        return trucks;
    }

    private static void checkIsCurrentTruckCountLessThenAvailable(Integer availableTruckCount, ArrayList<Truck> trucks) {
        if (trucks.size() >= availableTruckCount) {
            throw new RuntimeException(String.format("Too many packages for %d truck count", availableTruckCount));
        }
    }

    private void tryPlacePackage(Package packageRecord, Truck truck) {
        var fillingSlots = packageRecord.mapToListOfFillingSlots(TRUCK_BACK_WIDTH - 1, TRUCK_BACK_HEIGHT - 1);

        truck.fillBackTruckSlots(fillingSlots, packageRecord.getDescriptionNumber());

        truck.loadPackage(packageRecord);
    }
}
