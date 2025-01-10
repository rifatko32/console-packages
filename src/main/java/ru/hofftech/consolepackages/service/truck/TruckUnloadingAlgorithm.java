package ru.hofftech.consolepackages.service.truck;

import ru.hofftech.consolepackages.service.packageitem.Package;

import java.util.List;

/**
 * Algorithm to unload packages from trucks.
 *
 * <p>
 * This algorithm iterates through the list of trucks and returns a list of all packages
 * that are loaded in the trucks.
 * </p>
 */
public class TruckUnloadingAlgorithm {
    /**
     * Unloads packages from the given list of trucks.
     *
     * @param trucks the list of trucks from which to unload packages
     * @return a list of packages unloaded from the trucks
     */
    public List<Package> unloadTruck(List<Truck> trucks) {
        return trucks.stream().flatMap(t -> t.getPackages().stream()).toList();
    }
}
