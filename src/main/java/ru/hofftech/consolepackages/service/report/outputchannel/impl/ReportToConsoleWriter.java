package ru.hofftech.consolepackages.service.report.outputchannel.impl;

import ru.hofftech.consolepackages.service.report.PlaneStringReport;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriter;

public class ReportToConsoleWriter implements ReportWriter {

    @Override
    public void writeReport(PlaneStringReport report) {
        if (report == null || report.getReportStrings().isEmpty()) {
            throw new IllegalArgumentException("Report is null or empty");
        }

        writeReportToConsole(report);
    }

    private void writeReportToConsole(PlaneStringReport report) {
        for (var reportString : report.getReportStrings()) {
            System.out.println(reportString);
        }
    }
}
