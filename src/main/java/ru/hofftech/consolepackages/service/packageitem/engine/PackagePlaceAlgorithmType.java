package ru.hofftech.consolepackages.service.packageitem.engine;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum PackagePlaceAlgorithmType {
    PACKAGE_PLACE_BY_WIDTH("width"),
    SINGLE_PACKAGE_PER_TRUCK("single"),
    EQUAL_DISTRIBUTION("equal");

    public final String label;

    private PackagePlaceAlgorithmType(String label) {
        this.label = label;
    }

    private static final Map<String, PackagePlaceAlgorithmType> consoleValueToAlgorithmTypeMap = new HashMap<>();

    static {
        for (PackagePlaceAlgorithmType e : values()) {
            consoleValueToAlgorithmTypeMap.put(e.label, e);
        }
    }

    public static PackagePlaceAlgorithmType fromLabel(String label) {
        var algorithmType = consoleValueToAlgorithmTypeMap.get(label);

        if (algorithmType == null) {
            throw new IllegalArgumentException("No algorithm with console value " + label + " found.");
        }

        return algorithmType;
    }
}
