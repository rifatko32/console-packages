package ru.hofftech.consolepackages.service.packagetype;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import ru.hofftech.consolepackages.mapper.packagetype.PackageTypeMapper;
import ru.hofftech.consolepackages.model.dto.packagetype.CreatePackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.EditPackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.PackageTypeResponse;
import ru.hofftech.consolepackages.repository.PackageTypeRepository;

import java.util.List;

/**
 * The service provides methods to work with package types.
 */
@RequiredArgsConstructor
public class PackageTypeServiceImpl implements PackageTypeService {

    private final PackageTypeRepository packageTypeRepository;
    private final PackageTypeMapper packageTypeMapper;

    /**
     * Finds package type by id.
     *
     * @param id the id of the package type
     * @return the package type response
     */
    @Override
    public PackageTypeResponse findPackageType(Long id) {
        return packageTypeMapper.mapToResponse(packageTypeRepository.findById(id).orElse(null));
    }

    /**
     * Finds package types by parameters.
     *
     * @param page the number of page
     * @param size the size of page
     * @return the list of package types response
     */
    @Override
    public List<PackageTypeResponse> findPackageTypes(Integer page, Integer size) {
        return packageTypeRepository.findAll(PageRequest.of(page, size))
                .map(packageTypeMapper::mapToResponse)
                .getContent();
    }

    /**
     * Creates a package type.
     *
     * @param createPackageTypeDto the package type to create
     * @return the created package type response
     */
    @Override
    public PackageTypeResponse createPackageType(CreatePackageTypeDto createPackageTypeDto) {
        var packageType = packageTypeMapper.map(createPackageTypeDto);
        return packageTypeMapper.mapToResponse(packageTypeRepository.save(packageType));
    }

    /**
     * Edits a package type.
     *
     * @param id                 the id of the package type
     * @param editPackageTypeDto the package type to edit
     * @return the edited package type response
     */
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

    /**
     * Deletes a package type.
     *
     * @param id the id of the package type to be deleted
     */
    @Override
    public void deletePackageType(Long id) {
        packageTypeRepository.deleteById(id);
    }
}
