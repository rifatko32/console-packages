package ru.hofftech.consolepackages.service.command.impl.findpackagetype;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandParser;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;

@RequiredArgsConstructor
public class FindPackageTypeCommandFactory implements CommandAbstractFactory {

    private static final String NAME = "name";
    private static final String OUT_KEY = "out";
    private final PackageTypeRepository packageTypeRepository;
    private final ReportWriterFactory reportWriterFactory;

    @Override
    public Command createCommand(CommandContext commandContext) {
        return new FindPackageTypeCommand((FindPackageTypeContext) commandContext, packageTypeRepository, reportWriterFactory);
    }

    @Override
    public CommandContext createCommandContext(String strCommand) {
        var commandKeyValues = CommandParser.parseCommandKeys(strCommand);

        var channelType = ReportOutputChannelType.fromLabel(commandKeyValues.get(OUT_KEY));
        var name = commandKeyValues.get(NAME);

        return new FindPackageTypeContext(name, channelType);
    }
}
