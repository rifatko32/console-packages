package ru.hofftech.consolepackages.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.packageitem.Package;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmFactory;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmType;
import ru.hofftech.consolepackages.service.report.packageitem.PackagePlaceReportEngineFactory;
import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.util.PackageFileReader;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PackageFromFilePlaceService {
    private final PackageFileReader fileReader;
    private final PackagePlaceAlgorithmFactory placeEngineFactory;
    private final PackagePlaceReportEngineFactory reportEngineFactory;

    public PackagePlaceStringReport placePackages(
            String filePath,
            PackagePlaceAlgorithmType packagePlaceEngineType,
            ReportEngineType reportEngineType,
            Integer truckCount) {
        try {
            List<String> packages = fileReader.readPackages(filePath);
            if (packages.isEmpty()) {
                log.info("No packages found");
                return null;
            }

            log.info("Found {} packages, start of placing...", packages.size());

            var packagePlaceEngine = placeEngineFactory.createPackagePlaceEngine(packagePlaceEngineType);
            var packageRecords = mapToPackages(packages);
            var trucks = packagePlaceEngine.placePackages(packageRecords, truckCount);

            var reportEngine = reportEngineFactory.createReportEngine(reportEngineType);
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
