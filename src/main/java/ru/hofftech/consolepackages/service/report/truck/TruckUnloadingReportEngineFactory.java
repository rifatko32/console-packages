package ru.hofftech.consolepackages.service.report.truck;

import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.truck.impl.TruckUnloadingStringReportEngine;
import ru.hofftech.consolepackages.service.report.truck.impl.TruckUnloadingStringWithCountReportEngine;

/**
 * A factory for creating {@link TruckUnloadingReportEngine} instances based on the desired
 * report engine type.
 */
public class TruckUnloadingReportEngineFactory {
    /**
     * A factory for creating {@link TruckUnloadingReportEngine} instances based on the specified
     * {@link ReportEngineType}. *
     *
     * @see TruckUnloadingReportEngine
     * @see ReportEngineType
     */
    public TruckUnloadingReportEngine createReportEngine(ReportEngineType reportEngineType) {
        return switch (reportEngineType) {
            case STRING -> new TruckUnloadingStringReportEngine();
            case STRING_WITH_COUNT -> new TruckUnloadingStringWithCountReportEngine();
            default -> throw new IllegalArgumentException("Unknown engine type");
        };
    }
}
