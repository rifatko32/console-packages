package ru.hofftech.consolepackages.datastorage.repository;

import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;

import java.util.List;

public interface PackageTypeRepository {

    String create(
            String name,
            String form,
            String descriptionNumber,
            Integer width,
            Integer height);

    PackageType find(String name);

    List<PackageType> findAll();

    void updateForm(
            String name,
            String form);

    void updateDescriptionNumber(
            String name,
            String descriptionNumber);

    void updateSize(
            String name,
            Integer height,
            Integer width);

    void updatePackageType(PackageType packageType);

    void delete (String name);
}
