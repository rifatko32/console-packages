package ru.hofftech.consolepackages.datastorage.repository.impl;

import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryPackageTypeRepository implements PackageTypeRepository {

    private final Map<String, PackageType> packageTypes = new HashMap<>();

    @Override
    public String create(
            String name,
            String form,
            String descriptionNumber) {
        packageTypes.put(name, new PackageType(name, form, descriptionNumber));

        return name;
    }

    @Override
    public PackageType find(String name) {
        return packageTypes.get(name);
    }

    @Override
    public List<PackageType> findAll() {
        return packageTypes.values().stream().toList();
    }

    @Override
    public void updatePackageType(PackageType packageType) {
        packageTypes.put(packageType.getName(), packageType);
    }

    @Override
    public void delete(String name) {
        if (name == null || name.isEmpty() || !packageTypes.containsKey(name)) {
            return;
        }

        packageTypes.remove(name);
    }
}
