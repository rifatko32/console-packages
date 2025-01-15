package ru.hofftech.consolepackages.service.packageitem;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.model.Package;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to read packages from strings.
 */
@RequiredArgsConstructor
public class PackageFromStringReader {

    private final PackageFactory packageFactory;

    /**
     * Read packages from strings.
     *
     * @param packageString the string to read packages from
     * @return list of packages
     */
    public ArrayList<Package> readPackages(String packageString) {
        var packageParts = Arrays.stream(packageString.split(";")).toList();
        return packageFactory.generatePackages(packageParts);
    }
}
