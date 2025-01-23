package ru.hofftech.consolepackages.service.packageitem.engine.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.model.Truck;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PackagePlaceByWidthAlgorithmTest {

    @Test
    void testPlacePackageRecords_SimpleCase() {
        // Arrange
        PackagePlaceByWidthAlgorithm algorithm = new PackagePlaceByWidthAlgorithm();
        List<Package> packages = new ArrayList<>();
        packages.add(new Package("d", "typeName1", "999\\n999\\n999"));
        packages.add(new Package("d", "typeName2", "999\\n999"));

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck(10, 10));
        trucks.add(new Truck(10, 10));

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
        packages.add(new Package("x", "typeName1", "999\\n999\\n999"));
        packages.add(new Package("x", "typeName1", "999\\n999\\n999"));
        packages.add(new Package("x", "typeName1", "999\\n999\\n999"));

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck(3, 3));

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
        trucks.add(new Truck(10, 10));

        // Act
        algorithm.placePackageRecords(packages, trucks);

        // Assert
        assertThat(trucks.getFirst().getPackages()).isEmpty();
    }
}