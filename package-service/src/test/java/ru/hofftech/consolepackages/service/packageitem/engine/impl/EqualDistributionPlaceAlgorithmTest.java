package ru.hofftech.consolepackages.service.packageitem.engine.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.model.Truck;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

public class EqualDistributionPlaceAlgorithmTest {
    private final EqualDistributionPlaceAlgorithm algorithm = new EqualDistributionPlaceAlgorithm();

    @Test
    void testPlacePackageRecords_EqualNumberOfPackagesAndTrucks() {
        // Arrange
        List<Package> packages = new ArrayList<>();
        packages.add(new Package.Builder()
                .description("d")
                .typeId(1L)
                .form("999\\n999\\n999")
                .build());
        packages.add(new Package.Builder()
                .description("d")
                .typeId(1L)
                .form("999\\n999\\n999")
                .build());

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck.Builder().width(100).height(100).build());
        trucks.add(new Truck.Builder().width(100).height(100).build());

        // Act
        algorithm.placePackageRecords(packages, trucks);

        // Assert
        assertThat(trucks.get(0).getPackages()).hasSize(1);
        assertThat(trucks.get(1).getPackages()).hasSize(1);
    }

    @Test
    void testPlacePackageRecords_MorePackagesThanTrucks() {
        // Arrange
        List<Package> packages = new ArrayList<>();
        packages.add(new Package.Builder()
                .description("d")
                .typeId(1L)
                .form("999\\n999\\n999")
                .build());
        packages.add(new Package.Builder()
                .description("d")
                .typeId(1L)
                .form("999\\n999\\n999")
                .build());
        packages.add(new Package.Builder()
                .description("d")
                .typeId(1L)
                .form("999\\n999\\n999")
                .build());

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck.Builder().width(100).height(100).build());
        trucks.add(new Truck.Builder().width(100).height(100).build());

        // Act
        algorithm.placePackageRecords(packages, trucks);

        // Assert
        assertThat(trucks.get(0).getPackages()).hasSize(2);
        assertThat(trucks.get(1).getPackages()).hasSize(1);
    }

    @Test
    void testPlacePackageRecords_MoreTrucksThanPackages() {
        // Arrange
        List<Package> packages = new ArrayList<>();
        packages.add(new Package.Builder()
                .description("d")
                .typeId(1L)
                .form("999\\n999\\n999")
                .build());
        packages.add(new Package.Builder()
                .description("d")
                .typeId(1L)
                .form("999\\n999\\n999")
                .build());

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck.Builder().width(100).height(100).build());
        trucks.add(new Truck.Builder().width(100).height(100).build());
        trucks.add(new Truck.Builder().width(100).height(100).build());

        // Act
        algorithm.placePackageRecords(packages, trucks);

        // Assert
        assertThat(trucks.get(0).getPackages()).hasSize(1);
        assertThat(trucks.get(1).getPackages()).hasSize(1);
        assertThat(trucks.get(2).getPackages()).hasSize(0);
    }

    @Test
    void testPlacePackageRecords_EmptyPackagesList() {
        // Arrange
        List<Package> packages = new ArrayList<>();

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck.Builder().width(100).height(100).build());
        trucks.add(new Truck.Builder().width(100).height(100).build());

        // Act
        algorithm.placePackageRecords(packages, trucks);

        // Assert
        assertThat(trucks.get(0).getPackages()).hasSize(0);
        assertThat(trucks.get(1).getPackages()).hasSize(0);
    }

    @Test
    void testPlacePackageRecords_TooManyPackages() {
        // Arrange
        List<Package> packages = new ArrayList<>();
        packages.add(new Package.Builder()
                .description("d")
                .typeId(1L)
                .form("999\\n999\\n999")
                .build());
        packages.add(new Package.Builder()
                .description("d")
                .typeId(1L)
                .form("999\\n999\\n999")
                .build());

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck.Builder().width(3).height(3).build());

        // Act and Assert
        assertThatThrownBy(() -> algorithm.placePackageRecords(packages, trucks))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Too many packages for 1 truck count");
    }
}