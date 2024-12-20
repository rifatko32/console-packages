package ru.hofftech.consolepackages.service.packageitem.engine;

import ru.hofftech.consolepackages.service.packageitem.engine.impl.PackagePlaceByWidthAlgorithm;
import ru.hofftech.consolepackages.service.packageitem.engine.impl.SinglePackagePerTruckPlaceAlgorithm;

public class PackagePlaceAlgorithmFactory {
    public PackagePlaceAlgorithm createPackagePlaceEngine(PackagePlaceAlgorithmType engineType) {
        switch (engineType) {
            case PACKAGE_PLACE_BY_WIDTH -> {
                return new PackagePlaceByWidthAlgorithm();
            }
            case SINGLE_PACKAGE_PER_TRUCK -> {
                return new SinglePackagePerTruckPlaceAlgorithm();
            }
            default -> throw new IllegalArgumentException("Unknown engine type");
        }
    }
}
