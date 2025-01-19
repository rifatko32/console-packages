package ru.hofftech.consolepackages.model;

import lombok.Getter;
import ru.hofftech.consolepackages.service.packageitem.BackTruckSlot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a truck with specified dimensions and capabilities to load packages.
 * <p>
 * The truck contains a grid of slots that can be filled with package identifiers. It also
 * maintains a list of packages loaded onto it. The truck provides methods to load packages,
 * fill slots, and check slot statuses.
 * </p>
 */
@Getter
public class Truck {
    private final String[][] backTruckSlots; // координаты ячеек в кузове
    private final int width; //кол-во столбцов координата x
    private final int height; //кол-во строк координата y
    private final UUID id;

    private final List<Package> packages;

    public Truck(int width, int height) {
        id = UUID.randomUUID();
        this.width = width;
        this.height = height;
        backTruckSlots = new String[height][width];
        packages = new ArrayList<>();
    }

    /**
     * Loads a package onto the truck.
     *
     * @param packageItem the package to be loaded onto the truck
     */
    public void loadPackage(Package packageItem) {
        packages.add(packageItem);
    }


    /**
     * Fills the slots of the truck with the specified value.
     *
     * @param fillingSlots the slots to be filled
     * @param fillValue    the value to be used for filling the slots
     */
    public void fillBackTruckSlots(List<BackTruckSlot> fillingSlots, String fillValue) {
        for (var slot : fillingSlots) {
            backTruckSlots[slot.coordinateY()][slot.coordinateX()] = fillValue;
        }
    }


    /**
     * Checks if the slot with the specified coordinates is filled.
     *
     * @param x the x-coordinate of the slot
     * @param y the y-coordinate of the slot
     * @return true if the slot is filled, false otherwise
     */
    public boolean checkIsCurrentSlotIsFilled(int x, int y) {
        return backTruckSlots[y][x] != null;
    }

    /**
     * Checks if the range of slots with the specified coordinates is filled.
     *
     * @param startX the x-coordinate of the start of the range
     * @param startY the y-coordinate of the start of the range
     * @param endX   the x-coordinate of the end of the range
     * @param endY   the y-coordinate of the end of the range
     * @return true if the range has at least one filled slot, false otherwise
     */
    public boolean checkIsRangeHasFilledSlots(int startX, int startY, int endX, int endY) {
        var result = false;

        if (startX < 0 || startY < 0) {
            return result;
        }

        for (var x = startX; x <= endX; x++) {
            for (var y = startY; y <= endY; y++) {
                result = checkIsCurrentSlotIsFilled(x, y);
                if (result) {
                    return result;
                }
            }
        }

        return result;
    }

    /**
     * Checks if the range of slots with the specified coordinates has enough base.
     *
     * <p>
     * This method checks if the range of slots with the specified coordinates has
     * enough base to contain the package with the specified minimal base.
     * </p>
     *
     * @param startX the x-coordinate of the start of the range
     * @param endX   the x-coordinate of the end of the range
     * @param y      the y-coordinate of the range
     * @param minimalBase the minimal base of the package
     * @return true if the range has enough base to contain the package, false otherwise
     */
    public boolean checkIsHasEnoughBase(int startX, int endX, int y, int minimalBase) {
        if (y == height - 1) {
            return true;
        }

        var filledSlotCount = 0;
        for (var x = startX; x <= endX; x++) {
            if (checkIsCurrentSlotIsFilled(x, y + 1)) {
                filledSlotCount++;
            }
        }

        return filledSlotCount >= minimalBase;
    }

    /**
     * Retrieves the value of a specified slot in the truck's back.
     *
     * @param y the y-coordinate of the slot
     * @param x the x-coordinate of the slot
     * @return the value of the slot if it's filled, or a space character if it's empty
     */
    public String getBackTruckSlotValue(int y, int x) {
        return backTruckSlots[y][x] == null ? " " : backTruckSlots[y][x];
    }

    public Integer calcPackagesCount() {
        return packages.size();
    }
}
