package ru.hofftech.consolepackages.model.dto.billing;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record PackageBillDto(
        @NotNull
        List<Integer> packageVolumes,
        @NotNull
        UUID truckId) {
}
