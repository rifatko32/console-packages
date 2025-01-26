package ru.hofftech.consolepackages.service.command;

import ch.qos.logback.core.util.StringUtil;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CommandParametersValidator {

    /**
     * Class provides methods to validate parameters of commands.
     *
     * @throws IllegalArgumentException if the input file path is null or empty
     */
    public static void validateClientId(String clientId) {
        if (StringUtil.isNullOrEmpty(clientId)) {
            throw new IllegalArgumentException("Client id should be null or empty");
        }
    }

    /**
     * Validates the input file path.
     *
     * @param infile the input file path to validate
     * @throws IllegalArgumentException if the input file path is null or empty
     */
    public static void validateInfile(String infile) {
        if (StringUtil.isNullOrEmpty(infile)) {
            throw new IllegalArgumentException("infile id should be null or empty");
        }
    }

    /**
     * Validates the output file name.
     *
     * @param outFilename the output file name to validate
     * @throws IllegalArgumentException if the output file name is null or empty
     */
    public static void validateOutFilename(String outFilename) {
        if (StringUtil.isNullOrEmpty(outFilename)) {
            throw new IllegalArgumentException("out-filename id should be null or empty");
        }
    }

    /**
     * Validates the list of truck names.
     *
     * @param trucks the list of truck names to validate
     * @throws IllegalArgumentException if the list of truck names is null or empty
     */
    public static void validateTrucks(List<String> trucks) {
        if (trucks != null && !trucks.isEmpty()) {
            throw new IllegalArgumentException("trucks id should be null or empty");
        }
    }

    /**
     * Validates the packages text and file path.
     * <p>
     * Ensures that at least one of the parameters, either packagesText or filePath,
     * is not null or empty. Throws an IllegalArgumentException if both are null or empty.
     * </p>
     *
     * @param packagesText the text representation of the packages
     * @param filePath     the file path to the packages
     * @throws IllegalArgumentException if both packagesText and filePath are null or empty
     */
    public static void validatePackagesTextFilePath(String packagesText, String filePath) {
        if (StringUtil.isNullOrEmpty(packagesText) && StringUtil.isNullOrEmpty(filePath)) {
            throw new IllegalArgumentException("packagesText or filePath is null");
        }
    }

    /**
     * Validates the name of a package type.
     * <p>
     * Ensures that the name is not null or empty. Throws an IllegalArgumentException if the name is null or empty.
     * </p>
     *
     * @param name the name of the package type to validate
     * @throws IllegalArgumentException if the name is null or empty
     */
    public static void validateName(String name) {
        if (StringUtil.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("name is empty");
        }
    }

    /**
     * Validates the form of a package type.
     * <p>
     * Ensures that the form is not null or empty. Throws an IllegalArgumentException if the form is null or empty.
     * </p>
     *
     * @param form the form of the package type to validate
     * @throws IllegalArgumentException if the form is null or empty
     */
    public static void validateForm(String form) {
        if (StringUtil.isNullOrEmpty(form)) {
            throw new IllegalArgumentException("form is empty");
        }
    }

    /**
     * Validates the description of a package type.
     * <p>
     * Ensures that the description is not null or empty. Throws an IllegalArgumentException if the description is null or empty.
     * </p>
     *
     * @param description the description of the package type to validate
     * @throws IllegalArgumentException if the description is null or empty
     */
    public static void validateDescription(String description) {
        if (StringUtil.isNullOrEmpty(description)) {
            throw new IllegalArgumentException("description is empty");
        }
    }

    /**
     * Validates the form and description of a package type.
     * <p>
     * Ensures that at least one of the parameters, either form or description,
     * is not null or empty. Throws an IllegalArgumentException if both are null or empty.
     * </p>
     *
     * @param form the form of the package type to validate
     * @param description the description of the package type to validate
     * @throws IllegalArgumentException if both form and description are null or empty
     */
    public static void validateFormDescription(String form, String description) {
        if (StringUtil.isNullOrEmpty(form) && StringUtil.isNullOrEmpty(description)) {
            throw new IllegalArgumentException("form and description is null or empty");
        }
    }
}
