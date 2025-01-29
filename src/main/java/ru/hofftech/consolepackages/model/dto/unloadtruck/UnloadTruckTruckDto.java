package ru.hofftech.consolepackages.model.dto.unloadtruck;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record UnloadTruckTruckDto(
        @NotNull UUID id,
        Integer width,
        Integer height,
        @NotNull List<UnloadPackageDto> packages) {
}
