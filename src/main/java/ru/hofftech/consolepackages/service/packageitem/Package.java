package ru.hofftech.consolepackages.service.packageitem;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Package {
    private final UUID id;
    private final String description;
    private final int width;
    private final int height;
    private final String typeName;
    private final String form;

    public Package(
            String description,
            int width,
            int height,
            String typeName,
            String form) {
        id = UUID.randomUUID();

        this.width = width;
        this.height = height;
        this.typeName = typeName;
        this.form = form;
        this.description = description;
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

    @Override
    public String toString() {
        return typeName;
    }

    /**
     * // посылка 7 особенной формы
     * // 777
     * // 7777
     * // у ней д.б. пустой слот
     */
    private boolean checkIsSeventhPackageEmptySlot(int endX, int x, int y, int startY) {
        return x == endX && y == startY && Objects.equals(description, "7");
    }
}
