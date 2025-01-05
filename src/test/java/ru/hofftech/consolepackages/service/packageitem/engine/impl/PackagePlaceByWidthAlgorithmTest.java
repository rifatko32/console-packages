package ru.hofftech.consolepackages.service.packageitem.engine.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.service.packageitem.Package;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PackagePlaceByWidthAlgorithmTest {

    /*@Test
    public void placePackages_EmptyList_ReturnsEmptyList() {
        var algorithm = new PackagePlaceByWidthAlgorithm();
        List<Package> packages = new ArrayList<>();
        Integer availableTruckCount = 2;
        List<Truck> result = algorithm.placePackages(packages, availableTruckCount);
        assertThat(result).isEmpty();
    }

    @Test
    public void placePackages_SinglePackage_ReturnsListWithOneTruck() {
        var algorithm = new PackagePlaceByWidthAlgorithm();
        List<Package> packages = List.of(new Package(
                "1",
                1,
                1,
                "type 1",
                "1"));
        Integer availableTruckCount = 2;
        List<Truck> result = algorithm.placePackages(packages, availableTruckCount);
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getPackages()).hasSize(1);
    }

    @Test
    public void placePackages_MultiplePackages_ReturnsListOfTrucksWithPackages() {
        var algorithm = new PackagePlaceByWidthAlgorithm();
        List<Package> packages = List.of(
                new Package(
                        "1",
                        1,
                        1,
                        "type 1",
                        "1"),
                new Package(
                        "2",
                        2,
                        1,
                        "type 2",
                        "22"),
                new Package(
                        "3",
                        3,
                        1,
                        "type 3",
                        "333"),
                new Package(
                        "4",
                        4,
                        1,
                        "type 4",
                        "4444"));
        Integer availableTruckCount = 2;
        List<Truck> result = algorithm.placePackages(packages, availableTruckCount);
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getPackages()).hasSize(2);
        assertThat(result.get(1).getPackages()).hasSize(2);
    }

    @Test
    public void placePackages_TooManyPackages_ThrowsException() {
        var algorithm = new PackagePlaceByWidthAlgorithm();
        List<Package> packages = List.of(
                new Package(
                        "1",
                        1,
                        1,
                        "type 1",
                        "1"),
                new Package(
                        "2",
                        2,
                        1,
                        "type 2",
                        "22"),
                new Package(
                        "3",
                        3,
                        1,
                        "type 3",
                        "333"),
                new Package(
                        "4",
                        4,
                        1,
                        "type 4",
                        "4444"));
        Integer availableTruckCount = 1;
        assertThatThrownBy(() -> algorithm.placePackages(packages, availableTruckCount))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Too many packages for 1 truck count");
    }*/
}
