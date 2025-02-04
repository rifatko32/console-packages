package ru.hofftech.consolepackages.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static ru.hofftech.consolepackages.util.DateUtils.parseDate;

public class DateUtilsTest {

    @Test
    void testParseDate_ValidDate_ReturnsLocalDate() {
        // Arrange
        String dateString = "01.01.2025";

        // Act
        LocalDate date = parseDate(dateString);

        // Assert
        assertThat(date).isEqualTo(LocalDate.of(2025, 1, 1));
    }

    @Test
    void testParseDate_InvalidDate_ThrowsDateTimeParseException() {
        // Arrange
        String dateString = "32.01.2022";

        // Act and Assert
        assertThatThrownBy(() -> parseDate(dateString))
                .isInstanceOf(DateTimeParseException.class);
    }

    @Test
    void testParseDate_NullDate_ThrowsNullPointerException() {
        // Arrange
        String dateString = null;

        // Act and Assert
        assertThatThrownBy(() -> parseDate(dateString))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testParseDate_EmptyDate_ThrowsDateTimeParseException() {
        // Arrange
        String dateString = "";

        // Act and Assert
        assertThatThrownBy(() -> parseDate(dateString))
                .isInstanceOf(DateTimeParseException.class);
    }
}