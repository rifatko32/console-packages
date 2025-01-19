package ru.hofftech.consolepackages.controller;

import ch.qos.logback.core.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.AbstractFactoryProvider;
import ru.hofftech.consolepackages.service.command.CommandContextWithResult;
import ru.hofftech.consolepackages.service.command.impl.createpackagetype.CreatePackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.deletepackagetype.DeletePackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.editpackagetype.EditPackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.findpackagetype.FindPackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.placepackage.PlacePackageCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.unloadtruck.UnloadTruckCommandFactory;

/**
 * This component provides methods for Spring Shell to execute commands.
 * <p>
 * Spring Shell will find all methods annotated with {@link ShellMethod} and
 * expose them as commands.
 * </p>
 */
@RequiredArgsConstructor
@ShellComponent
public class ShellCommandController {

    private final AbstractFactoryProvider abstractFactoryProvider;

    /**
     * Returns a list of all package types.
     *
     * @return a list of all package types
     */
    @ShellMethod(key = "find")
    public String findPackageTypes(
            @ShellOption(value = {"--name"}) String name
    ) {
        var factory = abstractFactoryProvider.returnCommandAbstractFactory("find");

        var context = ((FindPackageTypeCommandFactory) factory).createCommandContextByParameters(name);
        var command = factory.createCommand(context);
        command.execute();

        return ((CommandContextWithResult<?>) context).getResult().toString();
    }

    /**
     * Returns a list of all package types.
     *
     * @param name the name of the package type
     * @return a list of all package types
     */
    @ShellMethod(key = "create")
    public String createPackageType(
            @ShellOption(value = {"--name"}) String name,
            @ShellOption(value = {"--form"}) String form,
            @ShellOption(value = {"--description"}) String description
    ) {
        var factory = abstractFactoryProvider.returnCommandAbstractFactory("create");

        var context = ((CreatePackageTypeCommandFactory) factory).createCommandContextByParameters(name, form, description);
        var command = factory.createCommand(context);
        command.execute();

        return String.format("Package type %s created", name);
    }

    /**
     * Deletes a package type.
     *
     * @param name the name of the package type
     * @return a message indicating that the package type was deleted
     */
    @ShellMethod(key = "delete")
    public String deletePackageType(
            @ShellOption(value = {"--name"}) String name
    ) {
        var factory = abstractFactoryProvider.returnCommandAbstractFactory("delete");

        var context = ((DeletePackageTypeCommandFactory) factory).createCommandContextByParameters(name);
        var command = factory.createCommand(context);
        command.execute();

        return String.format("Package type %s deleted", name);
    }

    /**
     * Edits a package type.
     *
     * @param name        the name of the package type
     * @param form        the new form of the package type
     * @param description the new description of the package type
     * @return a message indicating that the package type was edited
     */
    @ShellMethod(key = "edit")
    public String editPackageType(
            @ShellOption(value = {"--name"}) String name,
            @ShellOption(value = {"--form"}) String form,
            @ShellOption(value = {"--description"}) String description
    ) {
        var factory = abstractFactoryProvider.returnCommandAbstractFactory("edit");

        var context = ((EditPackageTypeCommandFactory) factory).createCommandContextByParameters(name, form, description);
        var command = factory.createCommand(context);
        command.execute();

        return String.format("Package type %s edited", name);
    }

    @ShellMethod(key = "load")
    public String loadPackages(
            @ShellOption(value = {"--packages-file"}, defaultValue = "") String filePath,
            @ShellOption(value = {"--trucks"}) String trucks,
            @ShellOption(value = {"--type"}) String type,
            @ShellOption(value = {"--out"}, defaultValue = "") String out,
            @ShellOption(value = {"--out-filename"}, defaultValue = "") String outFilename,
            @ShellOption(value = {"--packages-text"}, defaultValue = "") String packagesText,
            @ShellOption(value = {"--clientid"}, defaultValue = "") String clientId
    ) {
        var factory = abstractFactoryProvider.returnCommandAbstractFactory("load");

        var context = ((PlacePackageCommandFactory) factory).createCommandContextByParameters(trucks, type, filePath, out, outFilename, packagesText, clientId);
        var command = factory.createCommand(context);
        command.execute();

        var result = ((CommandContextWithResult<?>) context).getResult();
        return result == null ? "Packages loaded" : result.toString();
    }

    @ShellMethod(key = "unload")
    public String unloadTruck(
            @ShellOption(value = {"--infile"}, defaultValue = "") String inFilePath,
            @ShellOption(value = {"--outfile"}) String outfile,
            @ShellOption(value = {"--withcount"}, defaultValue = "") String withCount,
            @ShellOption(value = {"--clientid"}, defaultValue = "") String clientId
    ) {
        var factory = abstractFactoryProvider.returnCommandAbstractFactory("unload");

        var context = ((UnloadTruckCommandFactory) factory).createCommandContextByParameters(inFilePath, outfile, withCount, clientId);
        var command = factory.createCommand(context);
        command.execute();

        return "Trucks unloaded";
    }
}
