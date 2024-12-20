package ru.hofftech.consolepackages.service.packageitem.engine;

import ru.hofftech.consolepackages.service.packageitem.Package;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.List;

public interface PackagePlaceAlgorithm {
    List<Truck> placePackages(List<Package> packages);
}
