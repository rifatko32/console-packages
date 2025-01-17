package ru.hofftech.consolepackages.service.report.outputchannel;

import java.util.HashMap;
import java.util.Map;

public enum ReportOutputChannelType {
    CONSOLE("console"),
    JSON_FILE("json-file"),
    TXT_FILE("txt-file"),
    TG_BOT("tgbot");

    ReportOutputChannelType(String label) {
        this.label = label;
    }

    public final String label;

    private static final Map<String, ReportOutputChannelType> labelToValueMap = new HashMap<>();
    static {
        for (ReportOutputChannelType e : values()) {
            labelToValueMap.put(e.label, e);
        }
    }

    /**
     * Converts the console value to the report output channel type.
     *
     * @param label the console value.
     * @return the report output channel type.
     * @throws IllegalArgumentException if the console value is not found.
     */
    public static ReportOutputChannelType fromLabel(String label) {
        var channelType = labelToValueMap.get(label);

        if (channelType == null) {
            throw new IllegalArgumentException("No algorithm with console value " + label + " found.");
        }

        return channelType;
    }

}
