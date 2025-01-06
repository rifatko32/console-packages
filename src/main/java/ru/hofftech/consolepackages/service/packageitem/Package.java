package ru.hofftech.consolepackages.service.packageitem;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Package {
    private final static String SplitSymbol = "\\\\n";

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

        var fromParts = form.split(SplitSymbol);

        for (var y = 0; y < fromParts.length; y++) {
            for (var x = 0; x < fromParts[y].length(); x++) {
                if (fromParts[y].charAt(x) == ' ') {
                    continue;
                }
                result.add(new BackTruckSlot(x + startX, y + startY));
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
