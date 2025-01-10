package ru.hofftech.consolepackages.util;

import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class to read packages from a file.
 *
 * <p>
 * The class reads a file line by line and returns a list of strings, each of which
 * represents a package.
 * </p>
 */
@Slf4j
public class PackageFileReader {
    /**
     * Reads a file line by line and returns a list of strings, each of which
     * represents a package.
     *
     * @param filePath the path to the file with packages
     * @return list of strings representing packages
     */
    public List<String> readPackages(String filePath) {
        var result = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getClass().getClassLoader().getResource(filePath).toURI().getPath()))) {
            String line = reader.readLine();

            while (line != null) {
                result.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            log.error("Error while reading file", e);
            return Collections.emptyList();
        }
        return result;
    }
}
