package ru.hofftech.consolepackages.service.command.impl.placepackage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandContextWithResult;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmType;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class PlacePackageContext extends CommandContextWithResult<String> {
    private final List<String> trucks;
    private final PackagePlaceAlgorithmType algorithmType;
    private final String filePath;
    private final ReportEngineType reportEngineType;
    private final ReportOutputChannelType reportOutputChannelType;
    private final String outputFileName;
    private final String packagesText;
}
