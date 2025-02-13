package ru.hofftech.billing.model.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record PackageBillDto(
        @NotNull
        List<Double> packageVolumes,
        @NotNull
        UUID truckId) {
}
