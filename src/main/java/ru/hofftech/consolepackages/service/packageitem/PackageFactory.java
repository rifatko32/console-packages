package ru.hofftech.consolepackages.service.packageitem;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PackageFactory {

    private final PackageTypeRepository packageTypeRepository;

    public ArrayList<Package> generatePackages(List<String> packageStrings) {
        var packageTypes = packageTypeRepository.findByNames(packageStrings);
        var packages = new ArrayList<Package>();

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
