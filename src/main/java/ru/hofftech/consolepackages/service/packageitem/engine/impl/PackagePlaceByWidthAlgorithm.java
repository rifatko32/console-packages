package ru.hofftech.consolepackages.service.packageitem.engine.impl;

import ru.hofftech.consolepackages.service.packageitem.Package;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithm;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class PackagePlaceByWidthAlgorithm implements PackagePlaceAlgorithm {
    private final static int TRUCK_BACK_WIDTH = 6;
    private final static int TRUCK_BACK_HEIGHT = 6;

    @Override
    public List<Truck> placePackages(List<ru.hofftech.consolepackages.service.packageitem.Package> packages) {

        if (packages.isEmpty()) {
            return new ArrayList<>();
        }

        // сортируем по убыванию ширины посылок
        var sortedPackages = packages
                .stream()
                .sorted(Comparator.comparing(ru.hofftech.consolepackages.service.packageitem.Package::getWidth).reversed())
                .toList();

        return placeSortedPackages(sortedPackages);
    }

    private List<Truck> placeSortedPackages(List<ru.hofftech.consolepackages.service.packageitem.Package> packages) {
        var trucks = new ArrayList<Truck>();

        var placedPackagesIds = new HashSet<UUID>();

        do {
            var truck = new Truck(TRUCK_BACK_WIDTH, TRUCK_BACK_HEIGHT);

            for (ru.hofftech.consolepackages.service.packageitem.Package record : packages) {
                if (!placedPackagesIds.contains(record.getId()) && tryPlacePackage(record, truck)) {
                    placedPackagesIds.add(record.getId());
                }
            }

            trucks.add(truck);
        }
        while ((long) packages.size() != (long) placedPackagesIds.size());

        return trucks;
    }

    private boolean tryPlacePackage(ru.hofftech.consolepackages.service.packageitem.Package packageItem, Truck truck) {
        // пытаемся разместить посылку с правого нижнего угла кузова
        for (var y = truck.getHeight() - 1; y >= 0; y--) {
            for (var x = truck.getWidth() - 1; x >= 0; x--) {
                if (!checkIsPackageCouldBePlaced(x, y, packageItem, truck)) {
                    continue;
                }

                // packageItem.setPlaced(true);
                var fillingSlots = packageItem.mapToListOfFillingSlots(x, y);

                truck.fillBackTruckSlots(fillingSlots, packageItem.getDescriptionNumber());
                truck.loadPackage(packageItem);

                return true;
            }
        }

        return false;
    }

    private boolean checkIsPackageCouldBePlaced(
            int xCoordinate,
            int yCoordinate,
            Package packageItem,
            Truck truck) {
        return !truck.checkIsRangeHasFilledSlots(
                xCoordinate - packageItem.getWidth() + 1,
                yCoordinate - packageItem.getHeight() + 1,
                xCoordinate,
                yCoordinate)
                && packageItem.checkIsPackageHasEnoughSpace(
                xCoordinate + 1,
                yCoordinate + 1)
                && truck.checkIsHasEnoughBase(
                xCoordinate - packageItem.getWidth() + 1,
                xCoordinate,
                yCoordinate,
                packageItem.calcMinimalBase());
    }
}
