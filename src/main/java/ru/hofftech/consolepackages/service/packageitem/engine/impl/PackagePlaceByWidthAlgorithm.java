package ru.hofftech.consolepackages.service.packageitem.engine.impl;

import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithm;
import ru.hofftech.consolepackages.model.Truck;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Package placement algorithm that places packages in trucks by width.
 *
 * <p>
 * This algorithm tries to place package in truck from the right bottom corner
 * to the left top corner. If package could not be placed in current truck, the
 * algorithm tries to place package in the next truck.
 * </p>
 */
public class PackagePlaceByWidthAlgorithm extends PackagePlaceAlgorithm {

    @Override
    protected void placePackageRecords(List<Package> packages, List<Truck> trucks) {

        var placedPackagesIds = new HashSet<UUID>();

        for (Package record : packages) {
            var truckIdx = 0;
            do {
                var truck = trucks.get(truckIdx);
                truckIdx++;
                if (placedPackagesIds.contains(record.getId()) || !tryPlacePackage(record, truck)) {
                    continue;
                }
                placedPackagesIds.add(record.getId());
                truckIdx = 0;
            } while (truckIdx <= trucks.size() - 1);
        }

        if (placedPackagesIds.size() < packages.size()) {
            throw new RuntimeException(String.format("Too many packages for %d truck count", trucks.size()));
        }
    }

    private boolean tryPlacePackage(Package packageItem, Truck truck) {
        // пытаемся разместить посылку с правого нижнего угла кузова
        for (var y = truck.getHeight() - 1; y >= 0; y--) {
            for (var x = truck.getWidth() - 1; x >= 0; x--) {
                if (!checkIsPackageCouldBePlaced(x, y, packageItem, truck)) {
                    continue;
                }

                // packageItem.setPlaced(true);
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
