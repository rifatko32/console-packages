package ru.hofftech.consolepackages.model.dto.packagetype;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CreatePackageTypeDto(
        @NotBlank
        String name,
        @NotBlank
        String form,
        @NotBlank
        String description
) {
}
