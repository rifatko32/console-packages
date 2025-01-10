package ru.hofftech.consolepackages.service.packageitem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.util.PackageFileReader;

import java.util.ArrayList;
import java.util.List;

/**
 * The class implements the reader of packages from a file.
 */
@Slf4j
@RequiredArgsConstructor
public class PackageFromFileReader {
    private final PackageFileReader fileReader;
    private final PackageFactory packageFactory;

    /**
     * Read packages from a file.
     *
     * @param filePath the path to file with packages
     * @return list of packages
     */
    public ArrayList<Package> readPackages(String filePath) {
        try {
            var packageStrings = readFile(filePath);
            return packageFactory.generatePackages(packageStrings);
        } catch (Exception e) {
            log.error("Error while try to read packages from file", e);
        }

        return null;
    }

    /**
     * Read packages from a file.
     *
     * @param filePath the path to file with packages
     * @return list of packages
     */
    private List<String> readFile(String filePath) {
        var packageStrings = fileReader.readPackages(filePath);

        if (packageStrings.isEmpty()) {
            log.info("No packages found");
            return new ArrayList<>();
        }

        return packageStrings;
    }
}
