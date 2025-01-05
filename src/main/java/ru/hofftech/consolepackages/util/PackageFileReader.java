package ru.hofftech.consolepackages.util;

import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class PackageFileReader {
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
