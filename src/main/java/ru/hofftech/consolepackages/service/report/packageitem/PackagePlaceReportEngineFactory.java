package ru.hofftech.consolepackages.service.report.packageitem;

import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.packageitem.impl.PackagePlaceJsonReportEngine;
import ru.hofftech.consolepackages.service.report.packageitem.impl.PackagePlaceStringReportEngine;

public class PackagePlaceReportEngineFactory {
    public PackagePlaceReportEngine createReportEngine(ReportEngineType reportEngineType) {
        return switch (reportEngineType) {
            case STRING -> new PackagePlaceStringReportEngine();
            case JSON -> new PackagePlaceJsonReportEngine();
            default -> throw new IllegalArgumentException("Unknown engine type");
        };
    }
}
