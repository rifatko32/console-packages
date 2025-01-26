package ru.hofftech.consolepackages.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;

/**
 * Class to initialize data storage after startup.
 */
@RequiredArgsConstructor
public class StartupDataStorageInitializer {

    private final PackageTypeRepository packageTypeRepository;

    /**
     * Creates default package types and stores them in the repository.
     * This method initializes the data storage with a set of predefined package types,
     * each with a unique name, form, and description number. These package types are
     * created in the storage for use in the application.
     */
    @PostConstruct
    public void createDefaultPackageTypes() {
        packageTypeRepository.create(new PackageType.Builder()
                .name("type 1")
                .form("1")
                .descriptionNumber("1")
                .build());

        packageTypeRepository.create(new PackageType.Builder()
                .name("type 2")
                .form("22")
                .descriptionNumber("2")
                .build());

        packageTypeRepository.create(new PackageType.Builder()
                .name("type 3")
                .form("333")
                .descriptionNumber("3")
                .build());

        packageTypeRepository.create(new PackageType.Builder()
                .name("type 4")
                .form("4444")
                .descriptionNumber("4")
                .build());

        packageTypeRepository.create(new PackageType.Builder()
                .name("type 5")
                .form("55555")
                .descriptionNumber("5")
                .build());

        packageTypeRepository.create(new PackageType.Builder()
                .name("type 6")
                .form("666\\n666")
                .descriptionNumber("6")
                .build());

        packageTypeRepository.create(new PackageType.Builder()
                .name("type 7")
                .form("777\\n7777")
                .descriptionNumber("7")
                .build());
    }
}
