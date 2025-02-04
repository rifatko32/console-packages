package ru.hofftech.consolepackages.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.hofftech.consolepackages.service.command.CommandContextWithResult;
import ru.hofftech.consolepackages.service.command.impl.placepackage.PlacePackageCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.unloadtruck.UnloadTruckCommandFactory;

import static ru.hofftech.consolepackages.service.command.CommandConstants.LOAD_COMMAND_PATTERN;
import static ru.hofftech.consolepackages.service.command.CommandConstants.UNLOAD_COMMAND_PATTERN;
import static ru.hofftech.consolepackages.shellconstants.CommandOptions.CLIENT_ID;
import static ru.hofftech.consolepackages.shellconstants.CommandOptions.INFILE;
import static ru.hofftech.consolepackages.shellconstants.CommandOptions.OUT;
import static ru.hofftech.consolepackages.shellconstants.CommandOptions.OUT_FILENAME;
import static ru.hofftech.consolepackages.shellconstants.CommandOptions.PACKAGES_FILE;
import static ru.hofftech.consolepackages.shellconstants.CommandOptions.PACKAGES_TEXT;
import static ru.hofftech.consolepackages.shellconstants.CommandOptions.TRUCKS;
import static ru.hofftech.consolepackages.shellconstants.CommandOptions.TYPE;
import static ru.hofftech.consolepackages.shellconstants.CommandOptions.WITH_COUNT;

/**
 * This component provides methods for Spring Shell to execute commands.
 * <p>
 * Spring Shell will find all methods annotated with {@link ShellMethod} and
 * expose them as commands.
 * </p>
 */
@RequiredArgsConstructor
@ShellComponent
public class LoadCommandController {

    private final PlacePackageCommandFactory placePackageCommandFactory;
    private final UnloadTruckCommandFactory unloadTruckCommandFactory;

    /**
     * This method provides a command to load packages into a truck.
     * <p>
     * The command uses the {@link PlacePackageCommandFactory} service to
     * generate a report of the packages loaded into the truck.
     * </p>
     *
     * @param filePath     the path to the file containing package data
     * @param trucks       the number of trucks to use
     * @param type         the type of packages to load
     * @param out          the type of report to generate
     * @param outFilename  the filename of the report
     * @param packagesText the text representation of the packages to load
     * @param clientId     the client for which the packages are loaded
     * @return a string representation of the report
     */
    @ShellMethod(key = LOAD_COMMAND_PATTERN)
    public String loadPackages(
            @ShellOption(value = {PACKAGES_FILE}, defaultValue = "") String filePath,
            @ShellOption(value = {TRUCKS}) String trucks,
            @ShellOption(value = {TYPE}) String type,
            @ShellOption(value = {OUT}, defaultValue = "") String out,
            @ShellOption(value = {OUT_FILENAME}, defaultValue = "") String outFilename,
            @ShellOption(value = {PACKAGES_TEXT}, defaultValue = "") String packagesText,
            @ShellOption(value = {CLIENT_ID}, defaultValue = "") String clientId
    ) {
        var context = placePackageCommandFactory.createCommandContextByParameters(trucks, type, filePath, out, outFilename, packagesText, clientId);
        var command = placePackageCommandFactory.createCommand(context);
        command.execute();

        var result = ((CommandContextWithResult<?>) context).getResult();
        return result == null ? "Packages loaded" : result.toString();
    }

    /**
     * This method provides a command to unload packages from a truck.
     * <p>
     * The command uses the {@link UnloadTruckCommandFactory} service to
     * generate a report of the packages unloaded from the truck.
     * </p>
     *
     * @param inFilePath the path to the file containing truck data
     * @param outfile    the filename of the report
     * @param withCount  if the number of packages unloaded should be included in the report
     * @param clientId   the client for which the packages are unloaded
     * @return a string indicating that the truck has been unloaded
     */
    @ShellMethod(key = UNLOAD_COMMAND_PATTERN)
    public String unloadTruck(
            @ShellOption(value = {INFILE}) String inFilePath,
            @ShellOption(value = {OUT_FILENAME}) String outfile,
            @ShellOption(value = {WITH_COUNT}, defaultValue = "") String withCount,
            @ShellOption(value = {CLIENT_ID}) String clientId
    ) {
        var context = unloadTruckCommandFactory.createCommandContextByParameters(inFilePath, outfile, withCount, clientId);
        var command = unloadTruckCommandFactory.createCommand(context);
        command.execute();

        return "Trucks unloaded";
    }
}
