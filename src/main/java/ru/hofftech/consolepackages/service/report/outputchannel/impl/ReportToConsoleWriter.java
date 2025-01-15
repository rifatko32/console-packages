package ru.hofftech.consolepackages.service.report.outputchannel.impl;

import ru.hofftech.consolepackages.service.report.PlaneStringReport;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriter;

/**
 * Implementation of {@link ReportWriter} that writes reports to the console.
 */
public class ReportToConsoleWriter implements ReportWriter {


    /**
     * Implementation of {@link ReportWriter} that writes reports to the console.
     * <p>
     * This class provides functionality to write a {@link PlaneStringReport}
     * to the console. It ensures that the report is not null or empty before
     * writing it.
     * </p>
     */
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
