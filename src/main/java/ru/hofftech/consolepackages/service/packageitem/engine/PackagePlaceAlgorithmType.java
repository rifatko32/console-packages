package ru.hofftech.consolepackages.service.packageitem.engine;

import lombok.Getter;

@Getter
public enum PackagePlaceAlgorithmType {
    PACKAGE_PLACE_BY_WIDTH("width"),
    SINGLE_PACKAGE_PER_TRUCK("single"),
    EQUAL_DISTRIBUTION("equal");

    private final String consoleValue;

    PackagePlaceAlgorithmType(String value) {
        this.consoleValue = value;
    }

    public static PackagePlaceAlgorithmType fromConsoleValue(String consoleValue) {
        for (PackagePlaceAlgorithmType algorithmType : PackagePlaceAlgorithmType.values()) {
            if (algorithmType.getConsoleValue().equalsIgnoreCase(consoleValue)) {
                return algorithmType;
            }
        }
        throw new IllegalArgumentException("No algorithm with console value " + consoleValue + " found.");
    }
}
