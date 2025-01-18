package ru.hofftech.consolepackages.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
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
                "type 1",
                "1",
                "1");

        packageTypeRepository.create(
                "type 2",
                "22",
                "2");

        packageTypeRepository.create(
                "type 3",
                "333",
                "3");

        packageTypeRepository.create(
                "type 4",
                "4444",
                "4");

        packageTypeRepository.create(
                "type 5",
                "55555",
                "5");

        packageTypeRepository.create(
                "type 6",
                "666\\n666",
                "6");

        packageTypeRepository.create(
                "type 7",
                "777\\n7777",
                "7");

        packageTypeRepository.create(
                "type 8",
                "8888\\n8888",
                "8");

        packageTypeRepository.create(
                "type 9",
                "999\\n999\\n999",
                "9");

        packageTypeRepository.create(
                "type wide weight",
                "x  x\\nxxxx\\nx  x",
                "x");

        packageTypeRepository.create(
                "type narrow weight",
                "x x\\nxxx\\nx x",
                "x");

        packageTypeRepository.create(
                "type bracket",
                "xxxx\\nx  x",
                "x");
        packageTypeRepository.create(
                "type wheel",
                "xxx\\nx x\\nxxx",
                "y");
    }
}
