package ru.hofftech.consolepackages.service.truck;

import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.model.Truck;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TruckUnloadingAlgorithmTest {

    private final TruckUnloadingAlgorithm algorithm = new TruckUnloadingAlgorithm();

    @Test
    void testUnloadTruck_EmptyTrucksList_ReturnsEmptyList() {
        // Arrange
        List<Truck> trucks = new ArrayList<>();

        // Act
        List<Package> packages = algorithm.unloadTruck(trucks);

        // Assert
        assertThat(packages).isEmpty();
    }

    @Test
    void testUnloadTruck_SingleTruckWithNoPackages_ReturnsEmptyList() {
        // Arrange
        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck.Builder().width(100).height(100).build());

        // Act
        List<Package> packages = algorithm.unloadTruck(trucks);

        // Assert
        assertThat(packages).isEmpty();
    }

    @Test
    void testUnloadTruck_SingleTruckWithOnePackage_ReturnsListWithOnePackage() {
        // Arrange
        List<Truck> trucks = new ArrayList<>();
        Truck truck = new Truck.Builder().width(100).height(100).build();
        Package packageItem = new Package.Builder()
                .description("Example package")
                .typeId(1L)
                .form("xxx")
                .build();
        truck.loadPackage(packageItem);
        trucks.add(truck);

        // Act
        List<Package> packages = algorithm.unloadTruck(trucks);

        // Assert
        assertThat(packages).hasSize(1);
        assertThat(packages).containsExactly(packageItem);
    }

    @Test
    void testUnloadTruck_MultipleTrucksWithMultiplePackages_ReturnsListWithAllPackages() {
        // Arrange
        List<Truck> trucks = new ArrayList<>();
        Truck truck1 = new Truck.Builder().width(100).height(100).build();
        Package packageItem1 = new Package.Builder()
                .description("Package 1")
                .typeId(1L)
                .form("xx")
                .build();
        Package packageItem2 = new Package.Builder()
                .description("Package 2")
                .typeId(2L)
                .form("xxx")
                .build();
        truck1.loadPackage(packageItem1);
        truck1.loadPackage(packageItem2);
        trucks.add(truck1);

        Truck truck2 = new Truck.Builder().width(100).height(100).build();
        Package packageItem3 = new Package.Builder()
                .description("Package 3")
                .typeId(3L)
                .form("xxx\\nxxxx")
                .build();
        truck2.loadPackage(packageItem3);
        trucks.add(truck2);

        // Act
        List<Package> packages = algorithm.unloadTruck(trucks);

        // Assert
        assertThat(packages).hasSize(3);
        assertThat(packages).containsExactlyInAnyOrder(packageItem1, packageItem2, packageItem3);
    }
}