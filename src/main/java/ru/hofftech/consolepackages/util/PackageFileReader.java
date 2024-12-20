package ru.hofftech.consolepackages.util;

import lombok.extern.slf4j.Slf4j;

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
            var currentLine = new StringBuilder();

            while (line != null) {
                if (line.isEmpty()) {
                    result.add(currentLine.toString().trim());
                    currentLine.setLength(0);
                } else {
                    currentLine.append(line);
                    currentLine.append("\n");
                }

                line = reader.readLine();
            }

            if (!currentLine.isEmpty()) {
                result.add(currentLine.toString().trim());
            }
        } catch (Exception e) {
            log.error("Error while reading file", e);
            return Collections.emptyList();
        }
        return result;
    }
}
