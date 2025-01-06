package ru.hofftech.consolepackages.service.report.truck;

import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.truck.impl.TruckUnloadingStringReportEngine;
import ru.hofftech.consolepackages.service.report.truck.impl.TruckUnloadingStringWithCountReportEngine;

public class TruckUnloadingReportEngineFactory {
    public TruckUnloadingReportEngine createReportEngine(ReportEngineType reportEngineType) {
        return switch (reportEngineType) {
            case STRING -> new TruckUnloadingStringReportEngine();
            case STRING_WITH_COUNT -> new TruckUnloadingStringWithCountReportEngine();
            default -> throw new IllegalArgumentException("Unknown engine type");
        };
    }
}
