package ru.hofftech.billing.model.entity;

import java.util.EnumMap;
import java.util.Map;

public enum InboxMessageStatus {
    PENDING("Ожидание"),
    PROCESSED("Обработано");

    InboxMessageStatus(String label) {
        this.label = label;
    }

    public final String label;

    private static final Map<InboxMessageStatus, String> valueToLabelMap = new EnumMap<>(InboxMessageStatus.class);

    static {
        for (InboxMessageStatus e : values()) {
            valueToLabelMap.put(e, e.label);
        }
    }

    public static String returnLabel(InboxMessageStatus type) {
        return valueToLabelMap.get(type);
    }
}
