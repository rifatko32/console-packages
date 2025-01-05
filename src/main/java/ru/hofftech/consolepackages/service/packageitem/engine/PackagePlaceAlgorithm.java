package ru.hofftech.consolepackages.service.packageitem.engine;

import ru.hofftech.consolepackages.service.packageitem.Package;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.Comparator;
import java.util.List;

public abstract class PackagePlaceAlgorithm {

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
    };

    protected abstract void placePackageRecords(List<ru.hofftech.consolepackages.service.packageitem.Package> packages, List<Truck> trucks);
}
