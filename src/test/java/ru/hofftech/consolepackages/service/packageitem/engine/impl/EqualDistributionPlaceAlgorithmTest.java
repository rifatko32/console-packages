package ru.hofftech.consolepackages.service.packageitem.engine.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EqualDistributionPlaceAlgorithmTest {
    private final EqualDistributionPlaceAlgorithm algorithm = new EqualDistributionPlaceAlgorithm();

    /*@Test
    public void placePackages_EmptyList_ReturnsEmptyList() {
        List<ru.hofftech.consolepackages.service.packageitem.Package> packages = new ArrayList<>();
        Integer availableTruckCount = 2;
        List<Truck> result = algorithm.placePackages(packages, availableTruckCount);
        assertThat(result).isEmpty();
    }

    @Test
    public void placePackages_SinglePackage_ReturnsListWithOneTruck() {
        List<ru.hofftech.consolepackages.service.packageitem.Package> packages = List.of(new ru.hofftech.consolepackages.service.packageitem.Package(
                "1",
                1,
                1,
                "type 1",
                "1"));
        Integer availableTruckCount = 2;
        List<Truck> result = algorithm.placePackages(packages, availableTruckCount);
        assertThat(result).hasSize(2);
        assertThat(result.getFirst().getPackages()).hasSize(1);
    }

    @Test
    public void placePackages_MultiplePackages_ReturnsListOfTrucksWithPackages() {
        List<ru.hofftech.consolepackages.service.packageitem.Package> packages = List.of(
                new ru.hofftech.consolepackages.service.packageitem.Package(
                        "1",
                        1,
                        1,
                        "type 1",
                        "1"),
        new ru.hofftech.consolepackages.service.packageitem.Package(
                "2",
                2,
                1,
                "type 2",
                "22"),
        new ru.hofftech.consolepackages.service.packageitem.Package(
                "3",
                3,
                1,
                "type 3",
                "333"),
        new ru.hofftech.consolepackages.service.packageitem.Package(
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
    }*/
}