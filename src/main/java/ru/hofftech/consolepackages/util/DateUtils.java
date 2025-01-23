package ru.hofftech.consolepackages.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public final static String DATE_FORMAT = "yyyy.MM.dd";

    public static LocalDate parseDate(String dateString) {
        var formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(dateString, formatter);
    }
}
