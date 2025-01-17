package ru.hofftech.consolepackages.service.packageitem;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.model.Package;

import java.util.ArrayList;
import java.util.List;

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
        var packageTypes = packageTypeRepository.findByNames(packageStrings);
        var packages = new ArrayList<ru.hofftech.consolepackages.model.Package>();

        for (String packageString : packageStrings) {
            if (!packageTypes.containsKey(packageString)) {
                continue;
            }
            var curPackageType = packageTypes.get(packageString); //curPackageType
            packages.add(new Package(
                    curPackageType.getDescriptionNumber(),
                    curPackageType.getName(),
                    curPackageType.getForm()
            ));
        }
        return packages;
    }
}
