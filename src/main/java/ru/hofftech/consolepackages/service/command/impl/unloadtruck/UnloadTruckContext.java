package ru.hofftech.consolepackages.service.command.impl.unloadtruck;

import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;

public record UnloadTruckContext(
        String inFilePath,
        ReportEngineType reportEngineType,
        ReportOutputChannelType reportOutputChannelType,
        String outFilePath,
        boolean withCount) implements CommandContext {
}