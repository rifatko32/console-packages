package ru.hofftech.consolepackages.service.packageitem.engine.impl;

import ru.hofftech.consolepackages.service.packageitem.Package;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithm;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class EqualDistributionPlaceAlgorithm implements PackagePlaceAlgorithm {
    private final static int TRUCK_BACK_WIDTH = 6;
    private final static int TRUCK_BACK_HEIGHT = 6;

    @Override
    public List<Truck> placePackages(List<Package> packages, Integer availableTruckCount) {
        if (packages.isEmpty()) {
            return new ArrayList<>();
        }

        // сортируем по убыванию ширины посылок
        var sortedPackages = packages
                .stream()
                .sorted(Comparator.comparing((Package p) -> p.getHeight() * p.getWidth()).reversed())
                .toList();

        return placeSortedPackages(sortedPackages, availableTruckCount);
    }

    private List<Truck> placeSortedPackages(List<Package> packages, Integer availableTruckCount) {
        var trucks = generateTrucks(availableTruckCount);

        var placedPackagesIds = new HashSet<UUID>();

        var currentTruckIndex = 0;

        // 1. раскладываем по машинам равномерно
        placeVyEqualDistribution(packages, availableTruckCount, trucks, currentTruckIndex, placedPackagesIds);

        // 2. если остались нераспределенные посылки пытаемся распределить их по машинам
        placeRemainingPackages(packages, placedPackagesIds, trucks);

        if (placedPackagesIds.size() != packages.size()) {
            throw new RuntimeException(String.format("Too many packages for %d truck count", availableTruckCount));
        }

        return trucks;
    }

    private void placeRemainingPackages(List<Package> packages, HashSet<UUID> placedPackagesIds, ArrayList<Truck> trucks) {
        var nonePlacedPackages = packages.stream().filter(record -> !placedPackagesIds.contains(record.getId())).toList();

        for (Package record : nonePlacedPackages) {
            for (Truck truck : trucks) {
                if (!placedPackagesIds.contains(record.getId()) && tryPlacePackage(record, truck)) {
                    placedPackagesIds.add(record.getId());
                    break;
                }
            }
        }
    }

    private void placeVyEqualDistribution(List<Package> packages, Integer availableTruckCount, ArrayList<Truck> trucks, int currentTruckIndex, HashSet<UUID> placedPackagesIds) {
        for (Package record : packages) {

            var currentTruck = trucks.get(currentTruckIndex);

            if (!placedPackagesIds.contains(record.getId()) && tryPlacePackage(record, currentTruck)) {
                placedPackagesIds.add(record.getId());
            }

            currentTruckIndex++;
            if(currentTruckIndex >= availableTruckCount) {
                currentTruckIndex = 0;
            }
        }
    }

    private static ArrayList<Truck> generateTrucks(Integer availableTruckCount) {
        var trucks = new ArrayList<Truck>(availableTruckCount);

        for(var i = 0; i < availableTruckCount; i++) {
            var truck = new Truck(TRUCK_BACK_WIDTH, TRUCK_BACK_HEIGHT);
            trucks.add(truck);
        }

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
