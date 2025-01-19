package ru.hofftech.consolepackages.service.command.impl.findpackagetype;

import ch.qos.logback.core.util.StringUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.service.command.CommandContextWithResult;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;

import java.util.ArrayList;

/**
 * Context of the command to find a package type by name.
 */
@Getter
@Setter
@RequiredArgsConstructor
public class FindPackageTypeContext extends CommandContextWithResult<ArrayList<PackageType>> {
    private final String name;
    private final ReportOutputChannelType reportOutputChannelType;

    public static class Builder {
        private String name;
        private ReportOutputChannelType reportOutputChannelType;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder reportOutputChannelType(ReportOutputChannelType reportOutputChannelType) {
            this.reportOutputChannelType = reportOutputChannelType;
            return this;
        }

        public FindPackageTypeContext build() {
            if (StringUtil.isNullOrEmpty(name)){
                throw new IllegalArgumentException("name is null or empty");
            }

            if (reportOutputChannelType == null){
                reportOutputChannelType = ReportOutputChannelType.NONE;
            }

            return new FindPackageTypeContext(name, reportOutputChannelType);
        }
    }
}
