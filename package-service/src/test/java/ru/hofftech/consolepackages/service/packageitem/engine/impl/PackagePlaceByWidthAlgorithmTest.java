package ru.hofftech.consolepackages.service.packageitem.engine.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.model.Truck;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class PackagePlaceByWidthAlgorithmTest {

    @Test
    void testPlacePackageRecords_SimpleCase() {
        // Arrange
        PackagePlaceByWidthAlgorithm algorithm = new PackagePlaceByWidthAlgorithm();
        List<Package> packages = new ArrayList<>();
        packages.add(new Package.Builder()
                .description("d")
                .typeId(1L)
                .form("999\\n999\\n999")
                .build());
        packages.add(new Package.Builder()
                .description("d")
                .typeId(2L)
                .form("999\\n999")
                .build());

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck.Builder().width(10).height(10).build());
        trucks.add(new Truck.Builder().width(10).height(10).build());

        // Act
        algorithm.placePackageRecords(packages, trucks);

        // Assert
        assertThat(trucks.get(0).getPackages()).hasSize(2);
        assertThat(trucks.get(1).getPackages()).hasSize(0);
    }

    @Test
    void testPlacePackageRecords_TooManyPackages() {
        // Arrange
        PackagePlaceByWidthAlgorithm algorithm = new PackagePlaceByWidthAlgorithm();
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
        trucks.add(new Truck.Builder().width(3).height(3).build());

        // Act and Assert
        assertThatThrownBy(() -> algorithm.placePackageRecords(packages, trucks))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Too many packages for 1 truck count");
    }

    @Test
    void testPlacePackageRecords_NoPackages() {
        // Arrange
        PackagePlaceByWidthAlgorithm algorithm = new PackagePlaceByWidthAlgorithm();
        List<Package> packages = new ArrayList<>();
        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck.Builder().width(10).height(10).build());

        // Act
        algorithm.placePackageRecords(packages, trucks);

        // Assert
        assertThat(trucks.getFirst().getPackages()).isEmpty();
    }
}