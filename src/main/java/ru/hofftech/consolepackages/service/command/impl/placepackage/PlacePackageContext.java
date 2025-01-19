package ru.hofftech.consolepackages.service.command.impl.placepackage;

import ch.qos.logback.core.util.StringUtil;
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
@RequiredArgsConstructor
public class PlacePackageContext extends CommandContextWithResult<String> {
    private final List<String> trucks;
    private final PackagePlaceAlgorithmType algorithmType;
    private final String filePath;
    private final ReportEngineType reportEngineType;
    private final ReportOutputChannelType reportOutputChannelType;
    private final String outputFileName;
    private final String packagesText;

    public static class Builder {
        private List<String> trucks;
        private PackagePlaceAlgorithmType algorithmType;
        private String filePath;
        private ReportEngineType reportEngineType;
        private ReportOutputChannelType reportOutputChannelType;
        private String outputFileName;
        private String packagesText;

        public Builder trucks(List<String> trucks) {
            this.trucks = trucks;
            return this;
        }

        public Builder algorithmType(PackagePlaceAlgorithmType algorithmType) {
            this.algorithmType = algorithmType;
            return this;
        }

        public Builder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Builder reportEngineType(ReportEngineType reportEngineType) {
            this.reportEngineType = reportEngineType;
            return this;
        }

        public Builder reportOutputChannelType(ReportOutputChannelType reportOutputChannelType) {
            this.reportOutputChannelType = reportOutputChannelType;
            return this;
        }

        public Builder outputFileName(String outputFileName) {
            this.outputFileName = outputFileName;
            return this;
        }

        public Builder packagesText(String packagesText) {
            this.packagesText = packagesText;
            return this;
        }

        public PlacePackageContext build() {
            if (trucks == null || trucks.isEmpty()) {
                throw new IllegalArgumentException("trucks is null or empty");
            }

            if (algorithmType == null) {
                throw new IllegalArgumentException("algorithmType is null");
            }

            if (StringUtil.isNullOrEmpty(packagesText) && StringUtil.isNullOrEmpty(filePath)) {
                throw new IllegalArgumentException("packagesText or filePath is null");
            }

            return new PlacePackageContext(trucks, algorithmType, filePath, reportEngineType, reportOutputChannelType, outputFileName, packagesText);
        }
    }
}
