package ru.hofftech.consolepackages.service.packageitem;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PackageSizeCalculatorTest {

    @Test
    public void testCalcPackageTypeWidth_SimpleCase() {
        // Arrange
        String form = "10\\n20\\n30";

        // Act
        int width = PackageSizeCalculator.calcPackageTypeWidth(form);

        // Assert
        assertThat(width).isEqualTo(2);
    }

    @Test
    public void testCalcPackageTypeWidth_EmptyForm() {
        // Arrange
        String form = "";

        // Act
        int width = PackageSizeCalculator.calcPackageTypeWidth(form);

        // Assert
        assertThat(width).isEqualTo(0);
    }

    @Test
    public void testCalcPackageTypeWidth_NullForm() {
        // Act and Assert
        assertThatThrownBy(() -> PackageSizeCalculator.calcPackageTypeWidth(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testCalcPackageTypeHeight_SimpleCase() {
        // Arrange
        String form = "10\\n20\\n30";

        // Act
        int height = PackageSizeCalculator.calcPackageTypeHeight(form);

        // Assert
        assertThat(height).isEqualTo(3);
    }

    @Test
    public void testCalcPackageTypeHeight_EmptyForm() {
        // Arrange
        String form = "";

        // Act
        int height = PackageSizeCalculator.calcPackageTypeHeight(form);

        // Assert
        assertThat(height).isEqualTo(0);
    }

    @Test
    public void testCalcPackageTypeHeight_NullForm() {
        // Act and Assert
        assertThatThrownBy(() -> PackageSizeCalculator.calcPackageTypeHeight(null))
                .isInstanceOf(NullPointerException.class);
    }
}