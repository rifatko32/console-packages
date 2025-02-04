package ru.hofftech.billing.model.dto;

import java.util.List;
import java.util.UUID;

public record PackageBillDto(
        List<Double> packageVolumes,
        UUID truckId) {
}
