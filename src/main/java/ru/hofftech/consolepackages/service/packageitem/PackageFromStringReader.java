package ru.hofftech.consolepackages.service.packageitem;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;

@RequiredArgsConstructor
public class PackageFromStringReader {

    private final PackageFactory packageFactory;

    public ArrayList<Package> readPackages(String packageString) {
        var packageParts = Arrays.stream(packageString.split(";")).toList();
        return packageFactory.generatePackages(packageParts);
    }
}
