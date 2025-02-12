package ru.hofftech.consolepackages.service.packageitem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import ru.hofftech.consolepackages.model.entity.OperationType;
import ru.hofftech.consolepackages.mapper.loadpackage.TruckMapper;
import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.model.dto.placepackage.PlacePackagesFromFileDto;
import ru.hofftech.consolepackages.model.dto.placepackage.PlacePackagesFromTextDto;
import ru.hofftech.consolepackages.model.dto.placepackage.PlacePackagesResponse;
import ru.hofftech.consolepackages.service.outbox.OutboxMessageService;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmFactory;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmType;
import ru.hofftech.consolepackages.service.truck.TruckFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.hofftech.consolepackages.service.packageitem.PlacePackageConst.TRUCKS_DELIMITER;
import static ru.hofftech.consolepackages.service.report.truck.TruckConstants.PACKAGE_DELIMITER;

@Slf4j
@RequiredArgsConstructor
public class PlacePackageServiceImpl implements PlacePackageService {

    private final PackageFromStringReader packageFromStringReader;
    private final PackageFactory packageFactory;
    private final PackagePlaceAlgorithmFactory placeEngineFactory;
    private final OutboxMessageService outboxMessageService;
    private final TruckMapper truckMapper;

    @Override
    public PlacePackagesResponse placePackagesFromString(PlacePackagesFromTextDto loadPackagesFromTextDto) {
        log.info("Start of handling packages: {}", loadPackagesFromTextDto.packageText());

        var packages = packageFromStringReader.readPackages(loadPackagesFromTextDto.packageText());

        var trucks = handlePackages(loadPackagesFromTextDto.trucks(), loadPackagesFromTextDto.algorithmType(), packages, loadPackagesFromTextDto.clientId());

        log.info("End of handling packages: {}", loadPackagesFromTextDto.packageText());

        return new PlacePackagesResponse(truckMapper.mapTrucks(trucks));
    }

    private List<Truck> handlePackages(String loadPackagesFromTextDto, PackagePlaceAlgorithmType loadPackagesFromTextDto1, ArrayList<Package> packages, String loadPackagesFromTextDto2) {
        var truckParts = loadPackagesFromTextDto.split(TRUCKS_DELIMITER);
        var trucks = TruckFactory.createTrucks(Arrays.stream(truckParts).toList());

        var packagePlaceEngine = placeEngineFactory.createPackagePlaceEngine(loadPackagesFromTextDto1);
        packagePlaceEngine.placePackages(packages, trucks);

        outboxMessageService.createOutboxMessage(trucks, loadPackagesFromTextDto2, OperationType.LOAD);
        return trucks;
    }

    @Override
    public PlacePackagesResponse placePackagesFromFile(PlacePackagesFromFileDto loadPackagesFromFileDto, MultipartFile file) {
        log.info("Start of handling file: {}", loadPackagesFromFileDto.fileName());

        var packageParts = readPackageParts(file);

        var packages = packageFactory.generatePackages(packageParts);

        var trucks = handlePackages(loadPackagesFromFileDto.trucks(), loadPackagesFromFileDto.algorithmType(), packages, loadPackagesFromFileDto.clientId());

        log.info("End of handling file: {}", loadPackagesFromFileDto.fileName());

        return new PlacePackagesResponse(truckMapper.mapTrucks(trucks));
    }

    private static List<String> readPackageParts(MultipartFile file) {
        List<String> packageParts = null;
        try {
            packageParts = readFileContent(file);
        } catch (IOException e) {
            log.error("Error while reading file", e);
            throw new RuntimeException(e);
        }
        return packageParts;
    }

    private static List<String> readFileContent(MultipartFile file) throws IOException {
        String fileContent = new String(file.getBytes(), StandardCharsets.UTF_8);

        return List.of(fileContent.split(PACKAGE_DELIMITER));
    }
}
