package ru.hofftech.consolepackages.datastorage.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Comparator;

@Getter
public class PackageType {
    private final static String SplitSymbol = "\\\\n";

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

    private Integer calcPackageTypeWidth(String form) {
        var splitForm = form.split(SplitSymbol);

        return Arrays.stream(splitForm)
                .max(Comparator.comparingInt(String::length))
                .map(String::length)
                .orElse(0);
    }

    private Integer calcPackageTypeHeight(String form) {
        var splitForm = form.split(SplitSymbol);

        return splitForm.length;
    }
}
