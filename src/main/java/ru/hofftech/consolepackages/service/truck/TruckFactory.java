package ru.hofftech.consolepackages.service.truck;

import java.util.List;

public class TruckFactory {
    private static final String TRUCK_DELIMITER = "x";

    public static List<Truck> createTrucks(List<String> trucks) {
        return trucks.stream()
                .map(TruckFactory::createTruck)
                .toList();
    }

    private static Truck createTruck(String t) {
        String[] split = t.split(TRUCK_DELIMITER);
        return new Truck(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }
}
