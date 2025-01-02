package ru.hofftech.consolepackages.service;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;

@RequiredArgsConstructor
public class StartupDataStorageInitializer {

    private final PackageTypeRepository packageTypeRepository;

    public void init() {
        packageTypeRepository.create(
                "type 1",
                "1",
                "1",
                1,
                1
        );

        packageTypeRepository.create(
                "type 2",
                "22",
                "2",
                2,
                1
        );

        packageTypeRepository.create(
                "type 3",
                "333",
                "3",
                3,
                1);

        packageTypeRepository.create(
                "type 4",
                "4444",
                "4",
                4,
                1);

        packageTypeRepository.create(
                "type 5",
                "55555",
                "5",
                5,
                1);

        packageTypeRepository.create(
                "type 6",
                "666\n666",
                "6",
                3,
                2);

        packageTypeRepository.create(
                "type 7",
                "7777\n777",
                "7",
                3,
                2);

        packageTypeRepository.create(
                "type 8",
                "8888\n8888",
                "8",
                3,
                2);

        packageTypeRepository.create(
                "type 9",
                "999\n999\n999",
                "9",
                3,
                3);
    }
}
