package ru.hofftech.consolepackages.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.packageitem.Package;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmFactory;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmType;
import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;
import ru.hofftech.consolepackages.service.report.PackagePlaceStringReportEngine;
import ru.hofftech.consolepackages.util.PackageFileReader;
import ru.hofftech.consolepackages.util.ReportToConsoleWriter;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PackageFromFilePlaceService {
    private final PackageFileReader fileReader;
    private final PackagePlaceAlgorithmFactory placeEngineFactory;
    private final PackagePlaceStringReportEngine reportEngine;

    public PackagePlaceStringReport placePackages(String filePath, PackagePlaceAlgorithmType engineType) {
        try {
            List<String> packages = fileReader.readPackages(filePath);
            if (packages.isEmpty()) {
                log.info("No packages found");
                return null;
            }

            log.info("Found {} packages, start of placing...", packages.size());

            var packagePlaceEngine = placeEngineFactory.createPackagePlaceEngine(engineType);
            var packageRecords = mapToPackages(packages);
            var trucks = packagePlaceEngine.placePackages(packageRecords);

            return reportEngine.generateReport(trucks);
        } catch (Exception e) {
            log.error("Error while try to place packages", e);
        }

        return null;
    }

    private List<Package> mapToPackages(List<String> packages) {
        var result = new ArrayList<Package>();

        for (String curPackage : packages) {
            result.add(new Package(curPackage));
        }

        return result;
    }
}
