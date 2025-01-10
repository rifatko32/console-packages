package ru.hofftech.consolepackages.service.packageitem.engine.impl;

import ru.hofftech.consolepackages.service.packageitem.Package;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithm;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Algorithm to place packages into trucks, ensuring each truck receives only one package.
 * <p>
 * This algorithm iterates through the list of packages and assigns each package to the first
 * available truck that has not yet been loaded with a package. If all trucks are loaded, it
 * throws an exception indicating insufficient trucks.
 * </p>
 */
public class SinglePackagePerTruckPlaceAlgorithm extends PackagePlaceAlgorithm {

    protected void placePackageRecords(List<ru.hofftech.consolepackages.service.packageitem.Package> packages, List<Truck> trucks) {
        var loadedTruckSet = new HashSet<UUID>();

        for (var packageRecord : packages) {
            var truck = findUnloadedTruck(trucks, loadedTruckSet);
            tryPlacePackage(packageRecord, truck);
            loadedTruckSet.add(truck.getId());
        }
    }

    private static Truck findUnloadedTruck(List<Truck> trucks, HashSet<UUID> loadedTruckSet) {
        return trucks
                .stream()
                .filter(t -> !loadedTruckSet.contains(t.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Too many packages for %d truck count", trucks.size())));
    }

    private void tryPlacePackage(Package packageRecord, Truck truck) {
        var fillingSlots = packageRecord.mapToListOfFillingSlots(truck.getWidth() - 1, truck.getHeight() - 1);

        truck.fillBackTruckSlots(fillingSlots, packageRecord.getDescription());

        truck.loadPackage(packageRecord);
    }
}
