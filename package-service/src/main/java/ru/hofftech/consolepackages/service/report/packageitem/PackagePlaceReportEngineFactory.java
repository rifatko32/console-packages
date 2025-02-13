package ru.hofftech.consolepackages.service.report.packageitem;

import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.packageitem.impl.PackagePlaceJsonReportEngine;
import ru.hofftech.consolepackages.service.report.packageitem.impl.PackagePlaceStringReportEngine;

/**
 * A factory for creating {@link PackagePlaceReportEngine} instances based on the desired
 * report engine type.
 */
public class PackagePlaceReportEngineFactory {
    /**
     * Creates a {@link PackagePlaceReportEngine} instance based on the specified
     * {@link ReportEngineType}.
     *
     * @param reportEngineType the type of report engine to create.
     * @return the created report engine.
     */
    public PackagePlaceReportEngine createReportEngine(ReportEngineType reportEngineType) {
        return switch (reportEngineType) {
            case STRING -> new PackagePlaceStringReportEngine();
            case JSON -> new PackagePlaceJsonReportEngine();
            default -> throw new IllegalArgumentException("Unknown engine type");
        };
    }
}
