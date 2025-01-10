package ru.hofftech.consolepackages.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for reading a list of trucks from a JSON file.
 *
 * @author Alexey Stadnik
 */
@Slf4j
@RequiredArgsConstructor
public class TruckJsonFileReader {
    private final Gson gson;

    /**
     * Reads a list of trucks from a JSON file.
     *
     * @param filePath path to the JSON file containing the truck information
     * @return a list of {@link Truck}s read from the file
     */
    public List<Truck> readTrucks(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(getClass().getClassLoader().getResource(filePath).toURI().getPath()))) {
            return gson.fromJson(br, new TypeToken<List<Truck>>() {}.getType());
        } catch (Exception e) {
            log.error("Error while reading file", e);
            return Collections.emptyList();
        }
    }
}