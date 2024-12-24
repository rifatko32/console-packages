package ru.hofftech.consolepackages.service.report.outputchannel;

import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;

public interface ReportWriter {
    void writeReport(PackagePlaceStringReport report);
}
