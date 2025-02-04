package ru.hofftech.consolepackages.model.dto.packagetype;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePackageTypeDto(
        @NotNull
        Long id,
        @NotBlank
        String form,
        @NotBlank
        String descriptionNumber
) {
}
