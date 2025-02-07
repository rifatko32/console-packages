package ru.hofftech.consolepackages.datastorage.model.entity;

import java.util.EnumMap;
import java.util.Map;

public enum OutboxMessageStatus {
    PENDING("Ожидание"),
    PROCESSED("Обработаны");

    OutboxMessageStatus(String label) {
        this.label = label;
    }

    public final String label;

    private static final Map<OutboxMessageStatus, String> valueToLabelMap = new EnumMap<>(OutboxMessageStatus.class);

    static {
        for (OutboxMessageStatus e : values()) {
            valueToLabelMap.put(e, e.label);
        }
    }

    public static String returnLabel(OutboxMessageStatus type) {
        return valueToLabelMap.get(type);
    }
}
