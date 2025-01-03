package ru.hofftech.consolepackages.service.command.impl.unloadtruck;

import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;

public record UnloadTruckContext(
        String filePath,
        ReportEngineType reportEngineType,
        ReportOutputChannelType reportOutputChannelType,
        String fileExtension) implements CommandContext {
}
