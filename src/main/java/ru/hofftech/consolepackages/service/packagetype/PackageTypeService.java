package ru.hofftech.consolepackages.service.packagetype;

import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.model.dto.packagetype.CreatePackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.EditPackageTypeDto;

import java.util.List;

public interface PackageTypeService {

    PackageType findPackageType(String name);

    List<PackageType> findPackageTypes(Integer page, Integer size);

    PackageType createPackageType(CreatePackageTypeDto createPackageTypeDto);

    PackageType editPackageType(EditPackageTypeDto createPackageTypeDto);

    void deletePackageType(String name);
}
