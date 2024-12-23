package ru.hofftech.consolepackages.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.truck.Truck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class TruckJsonFileReader {
    public List<Truck> readTrucks(String filePath) {
        var gson = new Gson();
        List<Truck> trucks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(getClass().getClassLoader().getResource(filePath).toURI().getPath()))) {
            trucks = gson.fromJson(br, new TypeToken<List<Truck>>() {}.getType());
        } catch (Exception e) {
            log.error("Error while reading file", e);
            return Collections.emptyList();
        }
        return trucks;
    }
}