package ru.hofftech.consolepackages.service.report.packageitem.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.service.packageitem.Package;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PackagePlaceJsonReportEngineTest {
    @Test
    public void generateReport_givenTruckList_shouldReturnReport() {
        // Assert
        var packages = List.of(
                new Package("1"),
                new Package("2"),
                new Package("3"),
                new Package("5")
        );
        var truck = new Truck(6, 6);
        for (var packageItem : packages) {
            truck.loadPackage(packageItem);
        }
        var reportEngine = new PackagePlaceJsonReportEngine();

        // Act
        var report = reportEngine.generateReport(List.of(truck));

        // Assert
        assertThat(report.getReportStrings()).isNotEmpty();
    }
}
