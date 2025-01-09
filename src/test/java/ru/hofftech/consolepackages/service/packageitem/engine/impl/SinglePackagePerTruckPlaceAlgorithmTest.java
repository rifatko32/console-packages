package ru.hofftech.consolepackages.service.packageitem.engine.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.service.packageitem.Package;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SinglePackagePerTruckPlaceAlgorithmTest {

    private final SinglePackagePerTruckPlaceAlgorithm algorithm = new SinglePackagePerTruckPlaceAlgorithm();

    @Test
    public void testPlacePackageRecords_SimpleCase() {
        // Arrange
        List<Package> packages = new ArrayList<>();
        packages.add(new Package("d", 3, 3, "typeName1", "999\\n999\\n999"));
        packages.add(new Package("x", 3, 2, "typeName2", "999\\n999"));

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck(6, 6));
        trucks.add(new Truck(6, 6));

        // Act
        algorithm.placePackageRecords(packages, trucks);

        // Assert
        assertThat(trucks.get(0).getPackages()).hasSize(1);
        assertThat(trucks.get(1).getPackages()).hasSize(1);
    }

    @Test
    public void testPlacePackageRecords_TooManyPackages() {
        // Arrange
        List<Package> packages = new ArrayList<>();
        packages.add(new Package("x", 3, 3, "typeName1", "999\\n999\\n999"));
        packages.add(new Package("x", 3, 2, "typeName2", "999\\n999"));

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck(6, 6));

        // Act and Assert
        assertThatThrownBy(() -> algorithm.placePackageRecords(packages, trucks))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Too many packages for 1 truck count");
    }

    @Test
    public void testPlacePackageRecords_NoPackages() {
        // Arrange
        List<Package> packages = new ArrayList<>();
        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck(100, 100));

        // Act
        algorithm.placePackageRecords(packages, trucks);

        // Assert
        assertThat(trucks.get(0).getPackages()).isEmpty();
    }
}