package ru.hofftech.consolepackages.datastorage.model.entity;

import java.util.HashMap;
import java.util.Map;

public enum OperationType {
    LOAD("Погрузка"),
    UNLOAD("Разгрузка");

    OperationType(String label) {
        this.label = label;
    }

    public final String label;

    private static final Map<OperationType, String> valueToLabelMap = new HashMap<>();

    static {
        for (OperationType e : values()) {
            valueToLabelMap.put(e, e.label);
        }
    }

    public static String returnLabel(OperationType type) {
        return valueToLabelMap.get(type);
    }
}
