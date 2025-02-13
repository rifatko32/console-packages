package ru.hofftech.consolepackages.service.packageitem.engine.impl;

import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithm;
import ru.hofftech.consolepackages.model.Truck;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * {@link PackagePlaceAlgorithm} implementation that distribute packages across trucks in such a way that
 * each truck has nearly equal number of packages. The algorithm first distribute packages by equal distribution
 * and then try to place remaining packages one by one into each truck.
 */
public class EqualDistributionPlaceAlgorithm extends PackagePlaceAlgorithm {

    @Override
    protected void placePackageRecords(List<Package> packages, List<Truck> trucks) {

        var placedPackagesIds = new HashSet<UUID>();

        // 1. раскладываем по машинам равномерно
        placeVyEqualDistribution(packages, trucks, placedPackagesIds);

        // 2. если остались нераспределенные посылки пытаемся распределить их по машинам
        placeRemainingPackages(packages, placedPackagesIds, trucks);

        if (placedPackagesIds.size() != packages.size()) {
            throw new RuntimeException(String.format("Too many packages for %d truck count", trucks.size()));
        }
    }

    private void placeRemainingPackages(List<Package> packages, HashSet<UUID> placedPackagesIds, List<Truck> trucks) {
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

    private void placeVyEqualDistribution(List<Package> packages, List<Truck> trucks, HashSet<UUID> placedPackagesIds) {
        var currentTruckIndex = 0;

        for (Package record : packages) {

            var currentTruck = trucks.get(currentTruckIndex);

            if (!placedPackagesIds.contains(record.getId()) && tryPlacePackage(record, currentTruck)) {
                placedPackagesIds.add(record.getId());
            }

            currentTruckIndex++;
            if (currentTruckIndex >= trucks.size()) {
                currentTruckIndex = 0;
            }
        }
    }

    private boolean tryPlacePackage(Package packageItem, Truck truck) {
        // пытаемся разместить посылку с правого нижнего угла кузова
        for (var y = truck.getHeight() - 1; y >= 0; y--) {
            for (var x = truck.getWidth() - 1; x >= 0; x--) {
                if (!checkIsPackageCouldBePlaced(x, y, packageItem, truck)) {
                    continue;
                }

                var fillingSlots = packageItem.mapToListOfFillingSlots(x, y);

                truck.fillBackTruckSlots(fillingSlots, packageItem.getDescription());
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
