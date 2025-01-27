package ru.hofftech.consolepackages.service.command.impl.findpackagetype;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandParser;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;

import static ru.hofftech.consolepackages.service.command.CommandParametersValidator.validateId;

/**
 * The class implements the factory of commands for finding a package type by name.
 */
@RequiredArgsConstructor
public class FindPackageTypeCommandFactory implements CommandAbstractFactory {

    private static final String ID = "id";
    private static final String OUT_KEY = "out";
    private final PackageTypeRepository packageTypeRepository;
    private final ReportWriterFactory reportWriterFactory;

    /**
     * Creates the command to find a package type by name.
     *
     * @param commandContext the context of the command
     * @return the command to find a package type by name
     */
    @Override
    public Command createCommand(CommandContext commandContext) {
        return new FindPackageTypeCommand((FindPackageTypeContext) commandContext, packageTypeRepository, reportWriterFactory);
    }

    /**
     * Creates the context of the command to find a package type by name.
     *
     * @param strCommand the string containing the command
     * @return the context of the command to find a package type by name
     */
    @Override
    public CommandContext createCommandContext(String strCommand) {
        var commandKeyValues = CommandParser.parseCommandKeys(strCommand);

        var channelType = ReportOutputChannelType.fromLabel(commandKeyValues.get(OUT_KEY));
        var id = Long.parseLong(commandKeyValues.get(ID));

        validateId(id);

        return FindPackageTypeContext.builder()
                .id(id)
                .reportOutputChannelType(channelType)
                .build();
    }

    /**
     * Creates the context of the command to find a package type by name.
     *
     * @param id the name of the package type to find
     * @return the context of the command to find a package type by name
     */
    public CommandContext createCommandContextByParameters(Long id) {
        return FindPackageTypeContext.builder()
                .id(id)
                .reportOutputChannelType(ReportOutputChannelType.NONE)
                .build();
    }
}
