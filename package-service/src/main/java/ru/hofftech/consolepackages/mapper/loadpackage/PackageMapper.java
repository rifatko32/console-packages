package ru.hofftech.consolepackages.mapper.loadpackage;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.model.dto.placepackage.PlacePackageDto;
import ru.hofftech.consolepackages.model.dto.unloadtruck.UnloadPackageDto;

import java.util.List;

/**
 * Mapper interface for converting between Package and its DTO representations.
 * <p>
 * This mapper provides methods to convert a Package to its DTO form for placing packages
 * and unloading packages, as well as reverse mapping from these DTOs back to Package.
 * </p>
 *
 * <p>
 * The default method afterPlacePackageMapping is executed after mapping a PlacePackageDto
 * to a Package, creating a new Package instance with specific fields from PlacePackageDto.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface PackageMapper {

    @AfterMapping
    default Package afterPlacePackageMapping(PlacePackageDto placePackageDto) {
        return new Package(placePackageDto.getDescription(), placePackageDto.getTypeId(), placePackageDto.getForm());
    }

    PlacePackageDto mapPackage(Package packageItem);
    List<PlacePackageDto> mapListPackage(List<Package> packages);

    UnloadPackageDto mapUnloadPackage(Package packageItem);
    List<UnloadPackageDto> mapUnloadPackages(List<Package> packages);

    Package reverseMapUnloadPackage(UnloadPackageDto unloadPackageDto);
    List<Package> reverseMapUnloadPackages(List<UnloadPackageDto> unloadPackageDtos);
}
