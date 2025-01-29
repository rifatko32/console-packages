package ru.hofftech.consolepackages.service.packagetype;

import ru.hofftech.consolepackages.model.dto.packagetype.CreatePackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.EditPackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.PackageTypeResponse;

import java.util.List;

public interface PackageTypeService {

    PackageTypeResponse findPackageType(Long id);

    List<PackageTypeResponse> findPackageTypes(Integer page, Integer size);

    PackageTypeResponse createPackageType(CreatePackageTypeDto createPackageTypeDto);

    PackageTypeResponse editPackageType(Long id, EditPackageTypeDto createPackageTypeDto);

    void deletePackageType(Long id);
}
