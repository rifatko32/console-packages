package ru.hofftech.consolepackages.service.report.packageitem.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.service.report.PlaneStringReport;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PackagePlaceJsonReportEngineTest {

    private final PackagePlaceJsonReportEngine engine = new PackagePlaceJsonReportEngine();

    @Test
    void testGenerateReport_SimpleCase() {
        // Arrange
        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck.Builder().width(100).height(100).build());
        trucks.add(new Truck.Builder().width(100).height(100).build());

        // Act
        PlaneStringReport report = engine.generateReport(trucks);

        // Assert
        assertThat(report.getReportStrings()).isNotEmpty();
    }

    @Test
    void testGenerateReport_EmptyTrucks() {
        // Arrange
        List<Truck> trucks = new ArrayList<>();

        // Act
        PlaneStringReport report = engine.generateReport(trucks);

        // Assert
        assertThat(report.getReportStrings()).contains("[]");
    }

    @Test
    void testGenerateReport_NullTrucks() {
        // Act
        var report = engine.generateReport(null);

        // Assert
        assertThat(report.getReportStrings()).contains("null");
    }
}