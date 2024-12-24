package ru.hofftech.consolepackages.service.report.truck;

import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.truck.impl.TruckUnloadingStringReportEngine;

public class TruckUnloadingReportEngineFactory {
    public TruckUnloadingReportEngine createReportEngine(ReportEngineType reportEngineType) {
        return switch (reportEngineType) {
            case STRING -> new TruckUnloadingStringReportEngine();
            default -> throw new IllegalArgumentException("Unknown engine type");
        };
    }
}
