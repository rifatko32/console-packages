package ru.hofftech.consolepackages.datastorage.repository;

import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;

import java.util.List;
import java.util.Map;

/**
 * Interface for package type repository
 */
public interface PackageTypeRepository {

    /**
     * Creates new package type
     * @param packageType package type to create
     * @return created package type
     */
    PackageType create(PackageType packageType);

    /**
     * Finds package type in storage
     * @param name package type name
     * @return package type if found or null
     */
    PackageType find(String name);

    /**
     * Finds package types in storage
     * @param name list of package type names
     * @return map of package types where key is package type name and value is package type
     */
    Map<String, PackageType> findByNames(List<String> name);

    /**
     * Finds all package types in storage
     * @return list of package types
     */
    List<PackageType> findAll();

    /**
     * Updates package type in storage
     * @param packageType package type to update
     */
    void updatePackageType(PackageType packageType);

    /**
     * Deletes package type from storage
     * @param name package type name
     */
    void delete (String name);
}
