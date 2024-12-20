package ru.hofftech.consolepackages.util;

import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;

public class ReportToConsoleWriter {

    public void writeReportToConsole(PackagePlaceStringReport report) {
        if (report == null) {
            return;
        }

        for (var reportString : report.getReportStrings()) {
            System.out.println(reportString);
        }
    }
}
