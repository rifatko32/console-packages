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
        packageTypeRepository.create(
                new PackageType(
                        "type 1",
                        "1",
                        "1"));

        packageTypeRepository.create(
                new PackageType(
                        "type 2",
                        "22",
                        "2"));

        packageTypeRepository.create(
                new PackageType(
                        "type 3",
                        "333",
                        "3"));

        packageTypeRepository.create(
                new PackageType(
                        "type 4",
                        "4444",
                        "4"));

        packageTypeRepository.create(
                new PackageType(
                        "type 5",
                        "55555",
                        "5"));

        packageTypeRepository.create(
                new PackageType(
                        "type 6",
                        "666\\n666",
                        "6"));

        packageTypeRepository.create(
                new PackageType(
                        "type 7",
                        "777\\n7777",
                        "7"));

        packageTypeRepository.create(
                new PackageType(
                        "type 8",
                        "8888\\n8888",
                        "8"));

        packageTypeRepository.create(
                new PackageType(
                        "type 9",
                        "999\\n999\\n999",
                        "9"));

        packageTypeRepository.create(
                new PackageType(
                        "type wide weight",
                        "x  x\\nxxxx\\nx  x",
                        "x"));

        packageTypeRepository.create(
                new PackageType(
                        "type narrow weight",
                        "x x\\nxxx\\nx x",
                        "x"));

        packageTypeRepository.create(
                new PackageType(
                        "type bracket",
                        "xxxx\\nx  x",
                        "x"));

        packageTypeRepository.create(
                new PackageType(
                        "type wheel",
                        "xxx\\nx x\\nxxx",
                        "y"));
    }
}
