package ru.hofftech.consolepackages.service.packagetype;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.mapper.packagetype.PackageTypeMapper;
import ru.hofftech.consolepackages.model.dto.packagetype.CreatePackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.EditPackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.PackageTypeResponse;

import java.util.List;

@RequiredArgsConstructor
public class PackageTypeServiceImpl implements PackageTypeService {

    private final PackageTypeRepository packageTypeRepository;
    private final PackageTypeMapper packageTypeMapper;

    @Override
    public PackageTypeResponse findPackageType(Long id) {
        return packageTypeMapper.mapToResponse(packageTypeRepository.findById(id).orElse(null));
    }

    @Override
    public List<PackageTypeResponse> findPackageTypes(Integer page, Integer size) {
        return packageTypeRepository.findAll(PageRequest.of(page, size))
                .map(packageTypeMapper::mapToResponse)
                .getContent();
    }

    @Override
    public PackageTypeResponse createPackageType(CreatePackageTypeDto createPackageTypeDto) {
        var packageType = packageTypeMapper.map(createPackageTypeDto);
        return packageTypeMapper.mapToResponse(packageTypeRepository.save(packageType));
    }

    @Override
    public PackageTypeResponse editPackageType(Long id, EditPackageTypeDto editPackageTypeDto) {
        var packageType = this.packageTypeRepository.findById(id).orElse(null);

        if (packageType == null) {
            return null;
        }

        packageType.setForm(editPackageTypeDto.form());
        packageType.setDescriptionNumber(editPackageTypeDto.description());
        packageTypeRepository.save(packageType);

        return packageTypeMapper.mapToResponse(packageType);
    }

    @Override
    public void deletePackageType(Long id) {
        packageTypeRepository.deleteById(id);
    }
}
