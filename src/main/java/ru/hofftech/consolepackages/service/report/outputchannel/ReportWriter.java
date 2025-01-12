package ru.hofftech.consolepackages.service.report.outputchannel;

import ru.hofftech.consolepackages.service.report.PlaneStringReport;

public interface ReportWriter {
    void writeReport(PlaneStringReport report);
}
