package ru.hofftech.consolepackages.datastorage.repository.impl;

import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of {@link PackageTypeRepository} that stores package types in memory.
 */
public class InMemoryPackageTypeRepository implements PackageTypeRepository {

    private final Map<String, PackageType> packageTypes = new HashMap<>();

    /**
     * In-memory package type repository.
     *
     * @author Natalia.Strelkova
     */
    @Override
    public PackageType create(PackageType packageType) {
        packageTypes.put(packageType.getName(), packageType);

        return packageType;
    }

    /**
     * Finds package type in storage.
     * @param name package type name.
     * @return package type if found or null.
     */
    @Override
    public PackageType find(String name) {
        return packageTypes.get(name);
    }

    /**
     * Finds package types in storage.
     * @param names list of package type names.
     * @return map of package types where key is package type name and value is package type.
     */
    @Override
    public Map<String, PackageType> findByNames(List<String> names) {
        return packageTypes
                .entrySet()
                .stream()
                .filter(entry -> names.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toMap(PackageType::getName, p -> p));
    }

    /**
     * Finds all package types in storage.
     * @return list of package types.
     */
    @Override
    public List<PackageType> findAll() {
        return packageTypes.values().stream().toList();
    }

    /**
     * Updates package type in storage.
     * @param packageType package type to update.
     */
    @Override
    public void updatePackageType(PackageType packageType) {
        packageTypes.put(packageType.getName(), packageType);
    }

    /**
     * Deletes package type from storage.
     * @param name package type name.
     */
    @Override
    public void delete(String name) {
        if (name == null || name.isEmpty() || !packageTypes.containsKey(name)) {
            return;
        }

        packageTypes.remove(name);
    }
}
