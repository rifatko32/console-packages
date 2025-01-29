package ru.hofftech.consolepackages.model.dto.placepackage;

import jakarta.validation.constraints.NotBlank;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmType;

public record PlacePackagesFromFileDto(
        @NotBlank
        String fileName,
        @NotBlank
        String trucks,
        PackagePlaceAlgorithmType algorithmType,
        @NotBlank
        String clientId) {
}
