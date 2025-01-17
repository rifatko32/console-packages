package ru.hofftech.consolepackages.service.packageitem;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Class to calculate package type size.
 * <p>
 * This class has two methods to calculate package type width and height.
 * </p>
 */
public class PackageSizeCalculator {
    public final static String SplitSymbol = "\\\\n";

    /**
     * Calculates package type width. Width is the longest string length in given form.
     *
     * @param form form string to calculate width from
     * @return package type width
     */
    public static Integer calcPackageTypeWidth(String form) {
        var splitForm = form.split(SplitSymbol);

        return Arrays.stream(splitForm)
                .max(Comparator.comparingInt(String::length))
                .map(String::length)
                .orElse(0);
    }

    /**
     * Calculates package type height. Height is the number of strings in given form.
     *
     * @param form form string to calculate height from
     * @return package type height
     */
    public static Integer calcPackageTypeHeight(String form) {
        if (form.isEmpty()) {
            return 0;
        }

        var splitForm = form.split(SplitSymbol);

        return splitForm.length;
    }
}
