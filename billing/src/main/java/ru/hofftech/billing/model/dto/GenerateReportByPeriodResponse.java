package ru.hofftech.billing.model.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Report which contains strings representing packages in trucks.
 */
@Getter
public class GenerateReportByPeriodResponse {
    private final List<String> reportStrings;

    public GenerateReportByPeriodResponse() {
        reportStrings = new ArrayList<>();
    }


    /**
     * Add single string to report.
     *
     * @param reportString string to add
     */
    public void addReportString(String reportString) {
        reportStrings.add(reportString);
    }

    /**
     * Add all strings from list to report.
     *
     * @param reportStrings strings to add
     */
    public void addReportStrings(List<String> reportStrings) {
        this.reportStrings.addAll(reportStrings);
    }

    public String toPlainString() {
        return String.join("\n", reportStrings);
    }
}
