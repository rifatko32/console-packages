package ru.hofftech.consolepackages.mapper.loadpackage;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.model.dto.placepackage.PlacePackageDto;
import ru.hofftech.consolepackages.model.dto.unloadtruck.UnloadPackageDto;

import java.util.List;

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
