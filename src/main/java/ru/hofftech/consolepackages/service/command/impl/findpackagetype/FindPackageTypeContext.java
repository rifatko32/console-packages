package ru.hofftech.consolepackages.service.command.impl.findpackagetype;

import ch.qos.logback.core.util.StringUtil;
import lombok.Builder;
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
@Builder
@RequiredArgsConstructor
public class FindPackageTypeContext extends CommandContextWithResult<ArrayList<PackageType>> {
    private final Long id;
    private final ReportOutputChannelType reportOutputChannelType;
}
