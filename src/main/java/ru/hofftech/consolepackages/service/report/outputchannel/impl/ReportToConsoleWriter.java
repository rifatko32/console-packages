package ru.hofftech.consolepackages.service.report.outputchannel.impl;

import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriter;

public class ReportToConsoleWriter implements ReportWriter {

    @Override
    public void writeReport(PackagePlaceStringReport report) {
        if (report == null || report.getReportStrings().isEmpty()) {
            throw new IllegalArgumentException("Report is null or empty");
        }

        writeReportToConsole(report);
    }

    private void writeReportToConsole(PackagePlaceStringReport report) {
        for (var reportString : report.getReportStrings()) {
            System.out.println(reportString);
        }
    }
}
