package ru.hofftech.consolepackages.service.packagetype;

import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.model.dto.packagetype.CreatePackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.EditPackageTypeDto;

import java.util.List;

public class PackageTypeServiceImpl implements PackageTypeService {

    @Override
    public PackageType findPackageType(String name) {
        return null;
    }

    @Override
    public List<PackageType> findPackageTypes(Integer page, Integer size) {
        return List.of();
    }

    @Override
    public PackageType createPackageType(CreatePackageTypeDto createPackageTypeDto) {
        return null;
    }

    @Override
    public PackageType editPackageType(EditPackageTypeDto createPackageTypeDto) {
        return null;
    }

    @Override
    public void deletePackageType(String name) {

    }
}
