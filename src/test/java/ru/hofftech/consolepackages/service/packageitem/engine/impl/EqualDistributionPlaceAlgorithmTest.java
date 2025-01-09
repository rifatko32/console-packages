package ru.hofftech.consolepackages.service.packageitem.engine.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

import ru.hofftech.consolepackages.service.packageitem.Package;

public class EqualDistributionPlaceAlgorithmTest {
    private final EqualDistributionPlaceAlgorithm algorithm = new EqualDistributionPlaceAlgorithm();

    @Test
    public void testPlacePackageRecords_EqualNumberOfPackagesAndTrucks() {
        // Arrange
        List<Package> packages = new ArrayList<>();
        packages.add(new Package("d", "typeName", "999\\n999\\n999"));
        packages.add(new Package("d", "typeName", "999\\n999\\n999"));

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck(100, 100));
        trucks.add(new Truck(100, 100));

        // Act
        algorithm.placePackageRecords(packages, trucks);

        // Assert
        assertThat(trucks.get(0).getPackages()).hasSize(1);
        assertThat(trucks.get(1).getPackages()).hasSize(1);
    }

    @Test
    public void testPlacePackageRecords_MorePackagesThanTrucks() {
        // Arrange
        List<Package> packages = new ArrayList<>();
        packages.add(new Package("d", "typeName", "999\\n999\\n999"));
        packages.add(new Package("d", "typeName", "999\\n999\\n999"));
        packages.add(new Package("d", "typeName", "999\\n999\\n999"));

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck(100, 100));
        trucks.add(new Truck(100, 100));

        // Act
        algorithm.placePackageRecords(packages, trucks);

        // Assert
        assertThat(trucks.get(0).getPackages()).hasSize(2);
        assertThat(trucks.get(1).getPackages()).hasSize(1);
    }

    @Test
    public void testPlacePackageRecords_MoreTrucksThanPackages() {
        // Arrange
        List<Package> packages = new ArrayList<>();
        packages.add(new Package("d", "typeName", "999\\n999\\n999"));
        packages.add(new Package("d", "typeName", "999\\n999\\n999"));

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck(100, 100));
        trucks.add(new Truck(100, 100));
        trucks.add(new Truck(100, 100));

        // Act
        algorithm.placePackageRecords(packages, trucks);

        // Assert
        assertThat(trucks.get(0).getPackages()).hasSize(1);
        assertThat(trucks.get(1).getPackages()).hasSize(1);
        assertThat(trucks.get(2).getPackages()).hasSize(0);
    }

    @Test
    public void testPlacePackageRecords_EmptyPackagesList() {
        // Arrange
        List<Package> packages = new ArrayList<>();

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck(100, 100));
        trucks.add(new Truck(100, 100));

        // Act
        algorithm.placePackageRecords(packages, trucks);

        // Assert
        assertThat(trucks.get(0).getPackages()).hasSize(0);
        assertThat(trucks.get(1).getPackages()).hasSize(0);
    }

    @Test
    public void testPlacePackageRecords_TooManyPackages() {
        // Arrange
        List<Package> packages = new ArrayList<>();
        packages.add(new Package("d", "typeName", "999\\n999\\n999"));
        packages.add(new Package("d", "typeName", "999\\n999\\n999"));

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck(3, 3));

        // Act and Assert
        assertThatThrownBy(() -> algorithm.placePackageRecords(packages, trucks))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Too many packages for 1 truck count");
    }
}