package ru.hofftech.consolepackages.service.packagetype;

import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.model.dto.packagetype.CreatePackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.EditPackageTypeDto;

import java.util.List;

public interface PackageTypeService {

    PackageType findPackageType(Long id);

    List<PackageType> findPackageTypes(Integer page, Integer size);

    PackageType createPackageType(CreatePackageTypeDto createPackageTypeDto);

    PackageType editPackageType(Long id, EditPackageTypeDto createPackageTypeDto);

    void deletePackageType(Long id);
}
