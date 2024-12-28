package ru.hofftech.consolepackages.service.command.impl.placepackage;

import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmType;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;

import java.util.List;

public record PlacePackageContext(
        List<String> trucks,
        PackagePlaceAlgorithmType algorithmType,
        String filePath,
        ReportEngineType reportEngineType,
        ReportOutputChannelType reportOutputChannelType,
        String outputFileName,
        String packagesText) implements CommandContext {
}
