package ru.hofftech.consolepackages.datastorage.repository;

import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;

import java.util.List;
import java.util.Map;

public interface PackageTypeRepository {

    String create(
            String name,
            String form,
            String descriptionNumber);

    PackageType find(String name);

    Map<String, PackageType> findByNames(List<String> name);

    List<PackageType> findAll();

    void updatePackageType(PackageType packageType);

    void delete (String name);
}
