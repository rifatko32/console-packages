package ru.hofftech.consolepackages.model.dto.unloadtruck;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class UnloadPackageDto {
    @NotBlank
    private UUID id;
    @NotBlank
    private String description;
    private Integer width;
    private Integer height;
    @NotBlank
    private String typeName;
    @NotBlank
    private String form;
}
