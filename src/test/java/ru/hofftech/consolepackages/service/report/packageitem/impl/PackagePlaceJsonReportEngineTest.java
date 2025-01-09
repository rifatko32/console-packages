package ru.hofftech.consolepackages.service.report.packageitem.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PackagePlaceJsonReportEngineTest {

    private final PackagePlaceJsonReportEngine engine = new PackagePlaceJsonReportEngine();

    @Test
    public void testGenerateReport_SimpleCase() {
        // Arrange
        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck(100, 100));
        trucks.add(new Truck(100, 100));

        // Act
        PackagePlaceStringReport report = engine.generateReport(trucks);

        // Assert
        assertThat(report.getReportStrings()).isNotEmpty();
    }

    @Test
    public void testGenerateReport_EmptyTrucks() {
        // Arrange
        List<Truck> trucks = new ArrayList<>();

        // Act
        PackagePlaceStringReport report = engine.generateReport(trucks);

        // Assert
        assertThat(report.getReportStrings()).contains("[]");
    }

    @Test
    public void testGenerateReport_NullTrucks() {
        // Act
        var report = engine.generateReport(null);

        // Assert
        assertThat(report.getReportStrings()).contains("null");
    }
}