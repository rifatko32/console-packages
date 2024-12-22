package ru.hofftech.consolepackages.service.report;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PackagePlaceStringReport {
    private final List<String> reportStrings;

    public PackagePlaceStringReport() {
        reportStrings = new ArrayList<>();
    }

    public void addReportString(String reportString) {
        reportStrings.add(reportString);
    }

    public void addReportStrings(List<String> reportStrings) {
        this.reportStrings.addAll(reportStrings);
    }
}
