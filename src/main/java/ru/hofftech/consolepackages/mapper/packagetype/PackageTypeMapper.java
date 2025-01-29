package ru.hofftech.consolepackages.mapper.packagetype;

import org.mapstruct.Mapper;
import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.model.dto.packagetype.CreatePackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.PackageTypeResponse;

@Mapper(componentModel = "spring")
public interface PackageTypeMapper {

    PackageType map(CreatePackageTypeDto createPackageTypeDto);

    PackageTypeResponse mapToResponse(PackageType packageType);
}
