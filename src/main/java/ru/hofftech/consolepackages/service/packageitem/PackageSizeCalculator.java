package ru.hofftech.consolepackages.service.packageitem;

import java.util.Arrays;
import java.util.Comparator;

public class PackageSizeCalculator {
    public final static String SplitSymbol = "\\\\n";

    public static Integer calcPackageTypeWidth(String form) {
        var splitForm = form.split(SplitSymbol);

        return Arrays.stream(splitForm)
                .max(Comparator.comparingInt(String::length))
                .map(String::length)
                .orElse(0);
    }

    public static Integer calcPackageTypeHeight(String form) {
        if (form.isEmpty()) {
            return 0;
        }

        var splitForm = form.split(SplitSymbol);

        return splitForm.length;
    }
}
