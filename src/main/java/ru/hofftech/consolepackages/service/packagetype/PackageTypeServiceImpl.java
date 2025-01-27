package ru.hofftech.consolepackages.service.packagetype;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.mapper.loadpackage.PackageTypeMapper;
import ru.hofftech.consolepackages.model.dto.packagetype.CreatePackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.EditPackageTypeDto;

import java.util.List;

@RequiredArgsConstructor
public class PackageTypeServiceImpl implements PackageTypeService {

    private final PackageTypeRepository packageTypeRepository;
    private final PackageTypeMapper packageTypeMapper;

    @Override
    public PackageType findPackageType(Long id) {
        return packageTypeRepository.findById(id).orElse(null);
    }

    @Override
    public List<PackageType> findPackageTypes(Integer page, Integer size) {
        return packageTypeRepository.findAll();
    }

    @Override
    public PackageType createPackageType(CreatePackageTypeDto createPackageTypeDto) {
        var packageType = packageTypeMapper.map(createPackageTypeDto);
        return packageTypeRepository.save(packageType);
    }

    @Override
    public PackageType editPackageType(Long id, EditPackageTypeDto editPackageTypeDto) {
        var packageType = this.findPackageType(id);

        if (packageType == null) {
            return null;
        }

        packageType.setForm(editPackageTypeDto.form());
        packageType.setDescriptionNumber(editPackageTypeDto.description());
        packageTypeRepository.save(packageType);

        return packageType;
    }

    @Override
    public void deletePackageType(Long id) {
        packageTypeRepository.deleteById(id);
    }
}
