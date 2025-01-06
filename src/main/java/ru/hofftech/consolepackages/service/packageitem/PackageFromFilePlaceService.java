package ru.hofftech.consolepackages.service.packageitem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmFactory;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmType;
import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.packageitem.PackagePlaceReportEngineFactory;
import ru.hofftech.consolepackages.service.truck.Truck;
import ru.hofftech.consolepackages.util.PackageFileReader;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PackageFromFilePlaceService {
    private final PackageFileReader fileReader;
    private final PackagePlaceAlgorithmFactory placeEngineFactory;
    private final PackagePlaceReportEngineFactory reportEngineFactory;
    private final PackageTypeRepository packageTypeRepository;

    public PackagePlaceStringReport placePackages(
            String filePath,
            PackagePlaceAlgorithmType packagePlaceEngineType,
            ReportEngineType reportEngineType,
            List<Truck> trucks) {
        try {
            var packageStrings = readPackages(filePath);
            var packages = mapToPackages(packageStrings);
            log.info("Found {} packages, start of placing...", packages.size());

            var packagePlaceEngine = placeEngineFactory.createPackagePlaceEngine(packagePlaceEngineType);
            packagePlaceEngine.placePackages(packages, trucks);

            var reportEngine = reportEngineFactory.createReportEngine(reportEngineType);
            return reportEngine.generateReport(trucks);
        } catch (Exception e) {
            log.error("Error while try to place packages", e);
        }

        return null;
    }

    private List<String> readPackages(String filePath) {
        var packageStrings = fileReader.readPackages(filePath);

        if (packageStrings.isEmpty()) {
            log.info("No packages found");
            return new ArrayList<>();
        }

        return packageStrings;
    }

    private ArrayList<Package> mapToPackages(List<String> packageStrings) {
        var packageTypes = packageTypeRepository.findByNames(packageStrings);
        var packages = new ArrayList<Package>();

        for (String packageString : packageStrings) {
            if (!packageTypes.containsKey(packageString)) {
                continue;
            }
            var curPackageType = packageTypes.get(packageString); //curPackageType
            packages.add(new Package(
                    curPackageType.getDescriptionNumber(),
                    curPackageType.getWidth(),
                    curPackageType.getHeight(),
                    curPackageType.getName(),
                    curPackageType.getForm()
            ));
        }
        return packages;
    }
}
