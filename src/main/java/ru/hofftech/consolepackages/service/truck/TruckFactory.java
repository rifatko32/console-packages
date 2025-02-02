package ru.hofftech.consolepackages.service.truck;

import ru.hofftech.consolepackages.exception.TruckCreatingException;
import ru.hofftech.consolepackages.model.Truck;

import java.util.List;

/**
 * Factory for creating {@link Truck}s from a list of strings, where each string
 * represents a truck with its dimensions separated by a delimiter.
 */
public class TruckFactory {
    private static final String TRUCK_DELIMITER = "x";

    /**
     * Creates a list of {@link Truck} objects from a list of strings.
     * <p>
     * Each string in the input list represents a truck, where the dimensions
     * are separated by the specified delimiter.
     * </p>
     *
     * @param trucks a list of strings where each string represents a truck's dimensions
     * @return a list of {@link Truck} objects created from the input strings
     */
    public static List<Truck> createTrucks(List<String> trucks) {
        return trucks.stream()
                .map(TruckFactory::createTruck)
                .toList();
    }

    /**
     * Creates a {@link Truck} object from a string representing the truck's dimensions.
     * <p>
     * The input string should contain the dimensions separated by the specified delimiter.
     * The dimensions are expected to be integers representing the width and height of the truck.
     * </p>
     *
     * @param t a string representing the truck's dimensions
     * @return a {@link Truck} object with the specified dimensions
     */
    private static Truck createTruck(String t) {
        String[] split = t.split(TRUCK_DELIMITER);
        try {
            return new Truck.Builder()
                    .width(Integer.parseInt(split[0]))
                    .height(Integer.parseInt(split[1]))
                    .build();
        } catch (Exception e) {
            throw new TruckCreatingException(e);
        }
    }
}
