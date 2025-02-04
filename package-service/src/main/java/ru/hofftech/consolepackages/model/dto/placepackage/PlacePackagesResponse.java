package ru.hofftech.consolepackages.model.dto.placepackage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PlacePackagesResponse {
    private List<PlacePackageTruckDto> trucks;
}
