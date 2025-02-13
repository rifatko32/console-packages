package ru.hofftech.consolepackages.mapper.packagetype;

import org.mapstruct.Mapper;
import ru.hofftech.consolepackages.model.entity.PackageType;
import ru.hofftech.consolepackages.model.dto.packagetype.CreatePackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.PackageTypeResponse;

/**
 * Mapper interface for converting between CreatePackageTypeDto and PackageType,
 * as well as mapping PackageType to PackageTypeResponse.
 * <p>
 * This interface uses MapStruct for generating the implementation, with the
 * component model set to "spring" for integration with Spring framework.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface PackageTypeMapper {

    PackageType map(CreatePackageTypeDto createPackageTypeDto);

    PackageTypeResponse mapToResponse(PackageType packageType);
}
