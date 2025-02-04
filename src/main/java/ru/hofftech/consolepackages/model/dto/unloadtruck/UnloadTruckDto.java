package ru.hofftech.consolepackages.model.dto.unloadtruck;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UnloadTruckDto(
        @NotBlank
        String clientId,
        Boolean withCount,
        @NotNull
        List<UnloadTruckTruckDto> trucks) {
}
