package ru.hofftech.consolepackages.service.report;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Report which contains strings representing packages in trucks.
 *
 * @author Alexey Stadnik
 */
@Getter
public class PackagePlaceStringReport {
    private final List<String> reportStrings;

    public PackagePlaceStringReport() {
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
}
