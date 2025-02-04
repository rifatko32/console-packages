package ru.hofftech.consolepackages.service.packageitem.engine;

import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.model.Truck;

import java.util.Comparator;
import java.util.List;

/**
 * Algorithm to place packages into trucks based on package dimensions and truck capacity.
 */
public abstract class PackagePlaceAlgorithm {

    /**
     * Places packages into trucks based on package dimensions and truck capacity.
     *
     * <p>
     * The algorithm first sorts packages and trucks by their area in descending order.
     * Then it places the largest packages into the largest trucks available.
     * </p>
     *
     * @param packages list of packages to place
     * @param trucks list of trucks to place packages into
     */
    public void placePackages(List<Package> packages, List<Truck> trucks){

        if (packages.isEmpty() || trucks.isEmpty()) {
            return;
        }

        var sortedPackages = packages
                .stream()
                .sorted(Comparator.comparing((Package p) -> p.getHeight() * p.getWidth()).reversed())
                .toList();

        var sortedTrucks = trucks
                .stream()
                .sorted(Comparator.comparing((Truck t) -> t.getHeight() * t.getWidth()).reversed())
                .toList();

        placePackageRecords(sortedPackages, sortedTrucks);
    }

    protected abstract void placePackageRecords(List<Package> packages, List<Truck> trucks);
}
