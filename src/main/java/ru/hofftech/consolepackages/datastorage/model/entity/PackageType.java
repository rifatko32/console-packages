package ru.hofftech.consolepackages.datastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PackageType {
    private String name;
    private String form;
    private String descriptionNumber;
    private Integer width;
    private Integer height;
}
