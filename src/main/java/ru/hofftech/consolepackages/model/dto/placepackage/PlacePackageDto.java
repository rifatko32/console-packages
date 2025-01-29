package ru.hofftech.consolepackages.model.dto.placepackage;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PlacePackageDto {
    @NotBlank
    private UUID id;
    @NotBlank
    private String description;
    private Integer width;
    private Integer height;
    @NotBlank
    private String form;
    private Long typeId;
}
