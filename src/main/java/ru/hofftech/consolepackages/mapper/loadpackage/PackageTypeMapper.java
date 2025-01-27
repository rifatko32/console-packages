package ru.hofftech.consolepackages.mapper.loadpackage;

import org.mapstruct.Mapper;
import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.model.dto.packagetype.CreatePackageTypeDto;

@Mapper(componentModel = "spring")
public interface PackageTypeMapper {

    PackageType map(CreatePackageTypeDto createPackageTypeDto);
}
