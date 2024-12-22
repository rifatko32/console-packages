package ru.hofftech.consolepackages.service.report;

import ru.hofftech.consolepackages.service.report.impl.PackagePlaceJsonReportEngine;
import ru.hofftech.consolepackages.service.report.impl.PackagePlaceStringReportEngine;

public class PackagePlaceReportEngineFactory {
    public PackagePlaceReportEngine createReportEngine(ReportEngineType reportEngineType) {
        return switch (reportEngineType) {
            case STRING -> new PackagePlaceStringReportEngine();
            case JSON -> new PackagePlaceJsonReportEngine();
            default -> throw new IllegalArgumentException("Unknown engine type");
        };
    }
}
