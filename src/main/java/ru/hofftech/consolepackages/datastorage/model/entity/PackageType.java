package ru.hofftech.consolepackages.datastorage.model.entity;

import lombok.Getter;
import lombok.Setter;


import static ru.hofftech.consolepackages.service.packageitem.PackageSizeCalculator.calcPackageTypeHeight;
import static ru.hofftech.consolepackages.service.packageitem.PackageSizeCalculator.calcPackageTypeWidth;

@Getter
public class PackageType {

    @Setter
    private String name;
    private String form;
    @Setter
    private String descriptionNumber;
    private Integer width;
    private Integer height;

    public PackageType(String name, String form, String descriptionNumber) {
        this.name = name;
        this.form = form;
        this.descriptionNumber = descriptionNumber;
        this.width = calcPackageTypeWidth(form);
        this.height = calcPackageTypeHeight(form);
    }

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

    public void setForm(String form) {
        this.form = form;
        this.width = calcPackageTypeWidth(form);
        this.height = calcPackageTypeHeight(form);
    }
}
