package ru.hofftech.consolepackages.datastorage.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import static ru.hofftech.consolepackages.service.packageitem.PackageSizeCalculator.calcPackageTypeHeight;
import static ru.hofftech.consolepackages.service.packageitem.PackageSizeCalculator.calcPackageTypeWidth;

/**
 * Represents a type of package with specific dimensions and characteristics.
 * <p>
 * This class contains details about a package type, including its name, form,
 * description number, width, and height. The width and height are calculated
 * based on the package form.
 * </p>
 *
 * <p>
 * The class provides getters for all fields and setters for mutable fields
 * such as name and description number.
 * </p>
 *
 * @see ru.hofftech.consolepackages.service.packageitem.PackageSizeCalculator
 */
@Getter
@RequiredArgsConstructor
public class PackageType {

    @Setter
    private String name;
    private String form;
    @Setter
    private String descriptionNumber;
    private Integer width;
    private Integer height;

    private PackageType(String name, String form, String descriptionNumber) {
        this.name = name;
        this.form = form;
        this.descriptionNumber = descriptionNumber;
        width = calcPackageTypeWidth(form);
        height = calcPackageTypeHeight(form);
    }

    /**
     * Generates a string representation of the package type.
     * <p>
     * The representation includes the package type name, form, description number,
     * width, and height.
     * </p>
     * @return string representation of the package type
     */
    @Override
    public String toString() {
        return "PackageType{" +
                "name='" + name + '\'' +
                ", form='" + form + '\'' +
                ", descriptionNumber='" + descriptionNumber + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    /**
     * Updates the package type with the given form.
     * <p>
     * This method updates the package type with the given form and calculates
     * the new width and height based on the form.
     * </p>
     * @param form the new package form
     */
    public void setForm(String form) {
        this.form = form;
        width = calcPackageTypeWidth(form);
        height = calcPackageTypeHeight(form);
    }

    public static class Builder {
        private String name;
        private String form;
        private String descriptionNumber;
        private Integer width;
        private Integer height;

        public Builder() {}

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder form(String form) {
            this.form = form;
            return this;
        }

        public Builder descriptionNumber(String descriptionNumber) {
            this.descriptionNumber = descriptionNumber;
            return this;
        }

        public PackageType build() {
            return new PackageType(name, form, descriptionNumber);
        }
    }
}
