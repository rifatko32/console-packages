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
            String descriptionNumber,
            Integer width,
            Integer height) {
        packageTypes.put(name, new PackageType(name, form, descriptionNumber, width, height));

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
    public void updateForm(String name, String form) {
        if (form == null || form.isEmpty()) {
            return;
        }

        var packageType = packageTypes.get(name);

        packageType.setForm(form);
    }

    @Override
    public void updateDescriptionNumber(String name, String descriptionNumber) {
        if (descriptionNumber == null || descriptionNumber.isEmpty()) {
            return;
        }

        var packageType = packageTypes.get(name);

        packageType.setDescriptionNumber(descriptionNumber);
    }

    @Override
    public void updateSize(String name, Integer height, Integer width) {
        if(height == null || height <= 0 || width == null || width <= 0) {
            return;
        }

        var packageType = packageTypes.get(name);

        packageType.setWidth(width);
        packageType.setHeight(height);
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
