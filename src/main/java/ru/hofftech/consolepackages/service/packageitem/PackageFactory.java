package ru.hofftech.consolepackages.service.packageitem;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.model.Package;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Factory to generate packages from strings.
 */
@RequiredArgsConstructor
public class PackageFactory {

    private final PackageTypeRepository packageTypeRepository;

    /**
     * Generate packages from strings.
     *
     * @param packageStrings the strings to generate packages from
     * @return list of packages
     */
    public ArrayList<ru.hofftech.consolepackages.model.Package> generatePackages(List<String> packageStrings) {

        var packageTypeIds = packageStrings
                .stream()
                .map(String::trim)
                .map(Long::parseLong)
                .toList();

        var packageTypes = packageTypeRepository.findByIds(packageTypeIds)
                .stream()
                .collect(Collectors.toMap(PackageType::getId, p -> p));

        var packages = new ArrayList<ru.hofftech.consolepackages.model.Package>();

        for (var packageTypeId : packageTypeIds) {
            var curPackageType = packageTypes.get(packageTypeId);

            if (curPackageType == null) {
                continue;
            }

            packages.add(new Package(
                    curPackageType.getDescriptionNumber(),
                    curPackageType.getId(),
                    curPackageType.getForm()
            ));
        }
        return packages;
    }
}
