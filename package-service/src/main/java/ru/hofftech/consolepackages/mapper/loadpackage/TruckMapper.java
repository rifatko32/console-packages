package ru.hofftech.consolepackages.mapper.loadpackage;

import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.model.dto.placepackage.PlacePackageTruckDto;
import ru.hofftech.consolepackages.model.dto.unloadtruck.UnloadTruckTruckDto;

import java.util.List;

/**
 * Mapper for converting Truck objects to PlacePackageTruckDto objects.
 * PlacePackageTruckDto is a data transfer object that is used to represent the
 * trucks in the place package command.
 * <p>
 * This mapper provides methods to convert a Truck to its DTO form for placing
 * packages and reverse mapping from these DTOs back to Truck.
 * </p>
 */
@Mapper(componentModel = "spring", uses = PackageMapper.class)
public interface TruckMapper {

    PlacePackageTruckDto mapTruck(Truck truck);
    List<PlacePackageTruckDto> mapTrucks(List<Truck> trucks);

    @Mapping(source = "packages", target = "packages")
    Truck reverseMapTruck(@NotNull UnloadTruckTruckDto truckDto);
    List<Truck> reverseMapTrucks(@NotNull List<UnloadTruckTruckDto> trucks);
}
