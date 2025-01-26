package ru.hofftech.consolepackages.service.truck;

import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.model.dto.unloadtruck.UnloadTruckDto;
import ru.hofftech.consolepackages.model.dto.unloadtruck.UnloadTruckResponse;

import java.util.List;

public interface UnloadTruckService {

    List<Package> unloadTruck(List<Truck> trucks);

    UnloadTruckResponse unloadTruck(UnloadTruckDto unloadTruckDto);
}
