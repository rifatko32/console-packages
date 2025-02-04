package ru.hofftech.consolepackages.service.report.packageitem.impl;

import com.google.gson.GsonBuilder;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.service.report.PlaneStringReport;
import ru.hofftech.consolepackages.service.report.packageitem.PackagePlaceReportEngine;

import java.util.List;

/**
 * Implementation of {@link PackagePlaceReportEngine} that generates a report of packages in the form of a JSON string.
 * <p>
 * This report engine uses the Gson library to serialize the list of trucks into a JSON string. The JSON string is then
 * added to the report as a single string.
 * </p>
 */
public class PackagePlaceJsonReportEngine implements PackagePlaceReportEngine {
    /**
     * Implementation of {@link PackagePlaceReportEngine} that generates a report of packages in the form of a JSON string.
     *
     * <p>
     * This report engine uses the Gson library to serialize the list of trucks into a JSON string.
     * The JSON string is then added to the report as a single string.
     * </p>
     *
     * <p>
     * The generated report is encapsulated in a {@link PlaneStringReport} instance.
     * </p>
     *
     * @see PackagePlaceReportEngine
     */
    @Override
    public PlaneStringReport generateReport(List<Truck> trucks) {
        var report = new PlaneStringReport();

        var gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(trucks);

        report.addReportString(jsonString);

        return report;
    }
}
