package ru.hofftech.consolepackages.service.truck;

import kotlin.Pair;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.mapper.loadpackage.PackageMapper;
import ru.hofftech.consolepackages.mapper.loadpackage.TruckMapper;
import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.model.dto.unloadtruck.PackageCountResponse;
import ru.hofftech.consolepackages.model.dto.unloadtruck.UnloadTruckDto;
import ru.hofftech.consolepackages.model.dto.unloadtruck.UnloadTruckResponse;
import ru.hofftech.consolepackages.service.report.PlaneStringReport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to get packages from trucks.
 *
 * <p>
 * This service reads a list of trucks from a JSON file and applies a truck unloading algorithm
 * to unload packages from the trucks. It then generates a report of the unloaded packages based
 * on the specified report engine type.
 * </p>
 */
@Slf4j
@RequiredArgsConstructor
public class UnloadTruckServiceImpl implements UnloadTruckService {

    private final TruckUnloadingAlgorithm truckUnloadingAlgorithm;
    private final PackageMapper packageMapper;
    private final TruckMapper truckMapper;

    /**
     * Retrieves packages from trucks specified in the JSON file and generates a report.
     *
     * <p>
     * This method reads a list of trucks from the provided file path, applies an unloading
     * algorithm to retrieve the packages, and generates a report based on the specified
     * report engine type.
     * </p>
     *
     * @param trucks the trucks
     * @return a {@link PlaneStringReport} containing the details of the unloaded packages
     */
    public List<Package> unloadTruck(List<Truck> trucks) {
        try {
            if (trucks.isEmpty()) {
                log.info("No packages found");
                return null;
            }

            return truckUnloadingAlgorithm.unloadTruck(trucks);
        } catch (Exception e) {
            log.error("Error while try to unload trucks", e);
        }
        return null;
    }

    @Override
    public UnloadTruckResponse unloadTruck(UnloadTruckDto unloadTruckDto) {
        var result = new UnloadTruckResponse();

        var trucks = truckMapper.reverseMapTrucks(unloadTruckDto.trucks());
        var packages = unloadTruck(trucks);

        var packagePairs = calcPackages(packages);

        for (var pair : packagePairs) {
            result.addPackageCountResponse(PackageCountResponse.builder()
                    .packageTypeName(pair.getFirst())
                    .count(pair.getSecond())
                    .build());
        }

        return result;
    }

    public List<Pair<String, Integer>> calcPackages(List<Package> packages) {

        var result = new ArrayList<Pair<String, Integer>>();

        var groupedTypeNames = packages
                .stream()
                .map(Package::getTypeName)
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        var keys = new ArrayList<>(groupedTypeNames.keySet());

        for (String packageTypeName : keys) {
            var packageCount = groupedTypeNames.get(packageTypeName);
            result.add(new Pair<>(packageTypeName, packageCount.intValue()));
        }

        return result;
    }
}
