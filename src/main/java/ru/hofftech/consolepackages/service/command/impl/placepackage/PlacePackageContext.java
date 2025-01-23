package ru.hofftech.consolepackages.service.command.impl.placepackage;

import ch.qos.logback.core.util.StringUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hofftech.consolepackages.service.command.CommandContextWithResult;
import ru.hofftech.consolepackages.service.packageitem.engine.PackagePlaceAlgorithmType;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;

import java.util.List;

/**
 * Context of the command to place packages into trucks.
 * <p>
 * This context stores the properties to run the command:
 * <ul>
 *     <li>list of truck names</li>
 *     <li>algorithm type to use while placing packages</li>
 *     <li>path to the file with packages</li>
 *     <li>type of the report engine to use</li>
 *     <li>type of the report output channel to use</li>
 *     <li>name of the file to write the report to (if needed)</li>
 *     <li>text with packages to place</li>
 * </ul>
 * </p>
 */
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class PlacePackageContext extends CommandContextWithResult<String> {
    private final List<String> trucks;
    private final PackagePlaceAlgorithmType algorithmType;
    private final String filePath;
    private final ReportEngineType reportEngineType;
    private final ReportOutputChannelType reportOutputChannelType;
    private final String outputFileName;
    private final String packagesText;
    private final String clientId;
}
