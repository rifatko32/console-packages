package ru.hofftech.consolepackages.service.packageitem.engine.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.service.packageitem.Package;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SinglePackagePerTruckPlaceAlgorithmTest {
    @Test
    public void placePackages_givenPackages_shouldReturnFilledTruck() {
        // Arrange
        var packageStrings = List.of("22", "1");
        var engine = new SinglePackagePerTruckPlaceAlgorithm();
        int[][] expectedBackTruck1 = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 2, 2}};
        int[][] expectedBackTruck2 = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1}};

        var packages = new ArrayList<Package>();

        for (String curPackageString : packageStrings) {
            packages.add(new Package(curPackageString));
        }

        // Act
        var result = engine.placePackages(packages, 5);
        var truck1 = result.getFirst();
        var truck2 = result.get(1);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(truck1).isNotNull();
        assertThat(truck2).isNotNull();
        assertThat(truck1.getBackTruckSlots()).isEqualTo(expectedBackTruck1);
        assertThat(truck2.getBackTruckSlots()).isEqualTo(expectedBackTruck2);
    }

    @Test
    public void placePackages_givenEmptyListOfPackages_shouldReturnFilledTruck() {
        // Arrange
        var packages = new ArrayList<Package>();
        var engine = new PackagePlaceByWidthAlgorithm();

        // Act
        var result = engine.placePackages(packages, 6);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.size()).isZero();
    }
}
