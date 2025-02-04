package ru.hofftech.consolepackages.model;

import lombok.Getter;
import ru.hofftech.consolepackages.service.packageitem.BackTruckSlot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ru.hofftech.consolepackages.service.packageitem.PackageSizeCalculator.SplitSymbol;
import static ru.hofftech.consolepackages.service.packageitem.PackageSizeCalculator.calcPackageTypeHeight;
import static ru.hofftech.consolepackages.service.packageitem.PackageSizeCalculator.calcPackageTypeWidth;

/**
 * Class represents package that can be placed in truck.
 */
@Getter
public class Package {

    private final UUID id = UUID.randomUUID();
    private final String description;
    private final int width;
    private final int height;
    private final Long typeId;
    private final String form;

    public Package(
            String description,
            Long typeId,
            String form) {
        this.width = calcPackageTypeWidth(form);
        this.height = calcPackageTypeHeight(form);
        this.typeId = typeId;
        this.form = form;
        this.description = description;
    }

    /**
     * Check if package has enough space in truck slot.
     *
     * @param enoughWidth  minimal width of truck slot
     * @param enoughHeight minimal height of truck slot
     * @return true if package has enough space in truck slot, false otherwise.
     */
    public boolean checkIsPackageHasEnoughSpace(int enoughWidth, int enoughHeight) {
        return enoughWidth >= width && enoughHeight >= height;
    }

    /**
     * Calculates minimal available base of package.
     *
     * <p>
     * Minimal base of package is minimal width of truck slots that can contain package.
     * </p>
     *
     * @return minimal base of package
     */
    public int calcMinimalBase() {
        return (int) Math.ceil((double) width / 2);
    }

    /**
     * Maps package to list of filling slots.
     *
     * <p>
     * This method maps package to list of filling slots in truck.
     * </p>
     *
     * @param endX   x-coordinate of the end of package
     * @param endY   y-coordinate of the end of package
     * @return list of filling slots for package
     */
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

    /**
     * Calculates volume of package.
     *
     * <p>
     * Volume of package is the number of slots in the truck that the package takes.
     * </p>
     *
     * @return volume of package
     */
    public Integer calcVolume() {
        return mapToListOfFillingSlots(width - 1, height - 1).size();
    }

    /**
     * String representation of package.
     *
     * @return string representation of package
     */
    @Override
    public String toString() {
        return typeId.toString();
    }

    public static class Builder {
        private String description;
        private Long typeId;
        private String form;

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder typeId(Long typeId) {
            this.typeId = typeId;
            return this;
        }

        public Builder form(String form) {
            this.form = form;
            return this;
        }

        public Package build() {
            return new Package(description, typeId, form);
        }
    }
}
