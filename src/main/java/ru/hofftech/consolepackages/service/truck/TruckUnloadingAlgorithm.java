package ru.hofftech.consolepackages.service.truck;

import ru.hofftech.consolepackages.service.packageitem.Package;

import java.util.List;

public class TruckUnloadingAlgorithm {
    public List<Package> unloadTruck(List<Truck> trucks) {
        return trucks.stream().flatMap(t -> t.getPackages().stream()).toList();
    }
}
