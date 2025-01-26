package ru.hofftech.consolepackages.model.dto.packagetype;

import jakarta.validation.constraints.NotBlank;

public record EditPackageTypeDto(
        String form,
        String description
) {
}
