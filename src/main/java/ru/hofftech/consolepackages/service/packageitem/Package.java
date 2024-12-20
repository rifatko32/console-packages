package ru.hofftech.consolepackages.service.packageitem;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Package {
    private final UUID id;
    private final int descriptionNumber;
    private final int width;
    private final int height;

    public Package(String description) {
        id = UUID.randomUUID();
        descriptionNumber = Character.getNumericValue(description.charAt(0));

        switch (descriptionNumber) {
            case 1:
                width = 1;
                height = 1;
                break;
            case 2:
                width = 2;
                height = 1;
                break;
            case 3:
                width = 3;
                height = 1;
                break;
            case 4:
                width = 4;
                height = 1;
                break;
            case 5:
                width = 5;
                height = 1;
                break;
            case 6:
                width = 3;
                height = 2;
                break;
            case 7, 8:
                width = 4;
                height = 2;
                break;
            case 9:
                width = 3;
                height = 3;
                break;
            default:
                width = 0;
                height = 0;
        }
    }

    public boolean checkIsPackageHasEnoughSpace(int enoughWidth, int enoughHeight) {
        return enoughWidth >= width && enoughHeight >= height;
    }

    public int calcMinimalBase() {
        return (int) Math.ceil((double) width / 2);
    }

    public List<BackTruckSlot> mapToListOfFillingSlots(int endX, int endY) {
        var result = new ArrayList<BackTruckSlot>();

        var startX = endX - width + 1;
        var startY = endY - height + 1;

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                if (checkIsSeventhPackageEmptySlot(endX, x, y, startY)) {
                    continue;
                }
                result.add(new BackTruckSlot(x, y));
            }
        }

        return result;
    }

    /**
     * // посылка 7 особенной формы
     * // 777
     * // 7777
     * // у ней д.б. пустой слот
     */
    private boolean checkIsSeventhPackageEmptySlot(int endX, int x, int y, int startY) {
        return x == endX && y == startY && descriptionNumber == 7;
    }
}
