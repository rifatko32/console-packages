package ru.hofftech.consolepackages.model.entity;

import jakarta.persistence.Column;
import ru.hofftech.consolepackages.service.packageitem.PackageSizeCalculator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
 * @see PackageSizeCalculator
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PackageType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "form", nullable = false)
    private String form;

    @Column(name = "description_number", nullable = false)
    private String descriptionNumber;

    @Column(name = "width", nullable = false)
    private Integer width;

    @Column(name = "height", nullable = false)
    private Integer height;

    private PackageType(String form, String descriptionNumber) {
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
                "id='" + id + '\'' +
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
        private Long id;
        private String form;
        private String descriptionNumber;

        public Builder() {}

        public Builder id(Long id) {
            this.id = id;
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
            return new PackageType(form, descriptionNumber);
        }
    }
}
