package ru.hofftech.consolepackages.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public final static String DATE_FORMAT = "dd.MM.yyyy";

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param dateString the date string to parse, expected in the format "dd.MM.yyyy"
     * @return the parsed LocalDate object
     */
    public static LocalDate parseDate(String dateString) {
        var formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(dateString, formatter);
    }
}
