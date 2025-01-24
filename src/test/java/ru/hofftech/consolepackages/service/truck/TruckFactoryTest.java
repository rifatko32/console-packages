package ru.hofftech.consolepackages.service.truck;

import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.exception.TruckCreatingException;
import ru.hofftech.consolepackages.model.Truck;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TruckFactoryTest {

    @Test
    void testCreateTrucks_EmptyTrucksList_ReturnsEmptyList() {
        // Arrange
        List<String> trucksList = new ArrayList<>();

        // Act
        List<Truck> trucks = TruckFactory.createTrucks(trucksList);

        // Assert
        assertThat(trucks).isEmpty();
    }

    @Test
    void testCreateTrucks_SingleTruck_ReturnsListWithOneTruck() {
        // Arrange
        List<String> trucksList = new ArrayList<>();
        trucksList.add("10x10");

        // Act
        List<Truck> trucks = TruckFactory.createTrucks(trucksList);

        // Assert
        assertThat(trucks).hasSize(1);
        assertThat(trucks.getFirst().getWidth()).isEqualTo(10);
        assertThat(trucks.getFirst().getHeight()).isEqualTo(10);
    }

    @Test
    void testCreateTrucks_MultipleTrucks_ReturnsListWithAllTrucks() {
        // Arrange
        List<String> trucksList = new ArrayList<>();
        trucksList.add("10x10");
        trucksList.add("20x20");
        trucksList.add("30x30");

        // Act
        List<Truck> trucks = TruckFactory.createTrucks(trucksList);

        // Assert
        assertThat(trucks).hasSize(3);
        assertThat(trucks.get(0).getWidth()).isEqualTo(10);
        assertThat(trucks.get(0).getHeight()).isEqualTo(10);
        assertThat(trucks.get(1).getWidth()).isEqualTo(20);
        assertThat(trucks.get(1).getHeight()).isEqualTo(20);
        assertThat(trucks.get(2).getWidth()).isEqualTo(30);
        assertThat(trucks.get(2).getHeight()).isEqualTo(30);
    }

    @Test
    void testCreateTrucks_InvalidTruckFormat_MissingX_ThrowsTruckCreatingException() {
        // Arrange
        List<String> trucksList = new ArrayList<>();
        trucksList.add("10 10");

        // Act and Assert
        assertThatThrownBy(() -> TruckFactory.createTrucks(trucksList))
                .isInstanceOf(TruckCreatingException.class);
    }
}