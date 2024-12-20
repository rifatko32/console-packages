package ru.hofftech.consolepackages.service.truck;

import lombok.Getter;
import ru.hofftech.consolepackages.service.packageitem.BackTruckSlot;
import ru.hofftech.consolepackages.service.packageitem.Package;

import java.util.ArrayList;
import java.util.List;


public class Truck {
    @Getter
    private final int[][] backTruckSlots; // координаты ячеек в кузове
    @Getter
    private final int width; //кол-во столбцов координата x
    @Getter
    private final int height; //кол-во строк координата y

    private final List<Package> packages;

    public Truck(int width, int height) {
        this.width = width;
        this.height = height;
        backTruckSlots = new int[height][width];
        packages = new ArrayList<>();
    }

    public void loadPackage(Package packageItem) {
        packages.add(packageItem);
    }

    public void fillBackTruckSlots(List<BackTruckSlot> fillingSlots, int fillValue) {
        for (var slot : fillingSlots) {
            backTruckSlots[slot.coordinateY()][slot.coordinateX()] = fillValue;
        }
    }

    public boolean checkIsCurrentSlotIsFilled(int x, int y) {
        return backTruckSlots[y][x] != 0;
    }

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
}
