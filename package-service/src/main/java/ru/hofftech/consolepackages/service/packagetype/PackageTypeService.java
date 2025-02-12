package ru.hofftech.consolepackages.service.packagetype;

import ru.hofftech.consolepackages.model.dto.packagetype.CreatePackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.EditPackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.PackageTypeResponse;

import java.util.List;

/**
 * The interface provides methods to work with package types.
 */
public interface PackageTypeService {

    /**
     * Finds a package type by id.
     *
     * @param id the id of the package type
     * @return the package type response
     */
    PackageTypeResponse findPackageType(Long id);

    /**
     * Finds package types by parameters.
     *
     * @param page the number of page
     * @param size the size of page
     * @return the list of package types response
     */
    List<PackageTypeResponse> findPackageTypes(Integer page, Integer size);

    /**
     * Creates a package type.
     *
     * @param createPackageTypeDto the package type to create
     * @return the created package type response
     */
    PackageTypeResponse createPackageType(CreatePackageTypeDto createPackageTypeDto);

    /**
     * Edits a package type.
     *
     * @param id the id of the package type
     * @param createPackageTypeDto the package type to edit
     * @return the edited package type response
     */
    PackageTypeResponse editPackageType(Long id, EditPackageTypeDto createPackageTypeDto);

    /**
     * Deletes a package type.
     *
     * @param id the id of the package type
     */
    void deletePackageType(Long id);
}
