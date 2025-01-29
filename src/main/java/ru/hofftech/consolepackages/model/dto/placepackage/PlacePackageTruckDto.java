package ru.hofftech.consolepackages.model.dto.placepackage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;


public record PlacePackageTruckDto(
        @NotBlank UUID id,
        String[][] backTruckSlots,
        @NotNull List<PlacePackageDto> packages,
        int width,
        int height) {
}
