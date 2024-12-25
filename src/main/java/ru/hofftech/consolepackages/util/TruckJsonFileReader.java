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

@Slf4j
@RequiredArgsConstructor
public class TruckJsonFileReader {
    private final Gson gson;

    public List<Truck> readTrucks(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(getClass().getClassLoader().getResource(filePath).toURI().getPath()))) {
            return gson.fromJson(br, new TypeToken<List<Truck>>() {}.getType());
        } catch (Exception e) {
            log.error("Error while reading file", e);
            return Collections.emptyList();
        }
    }
}