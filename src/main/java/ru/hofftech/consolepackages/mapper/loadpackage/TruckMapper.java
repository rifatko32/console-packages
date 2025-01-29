package ru.hofftech.consolepackages.mapper.loadpackage;

import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.model.dto.placepackage.PlacePackageTruckDto;
import ru.hofftech.consolepackages.model.dto.unloadtruck.UnloadTruckTruckDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = PackageMapper.class)
public interface TruckMapper {

    PlacePackageTruckDto mapTruck(Truck truck);
    List<PlacePackageTruckDto> mapTrucks(List<Truck> trucks);

    @Mapping(source = "packages", target = "packages")
    Truck reverseMapTruck(@NotNull UnloadTruckTruckDto truckDto);
    List<Truck> reverseMapTrucks(@NotNull List<UnloadTruckTruckDto> trucks);
}
