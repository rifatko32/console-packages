package ru.hofftech.consolepackages.service.command.impl.placepackage;

import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmType;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;

public record PlacePackageContext (
        Integer truckCount,
        PackagePlaceAlgorithmType algorithmType,
        String filePath,
        ReportEngineType reportEngineType,
        ReportOutputChannelType reportOutputChannelType,
        String fileExtension) implements CommandContext {
}
