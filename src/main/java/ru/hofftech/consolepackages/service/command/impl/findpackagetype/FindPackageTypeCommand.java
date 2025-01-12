package ru.hofftech.consolepackages.service.command.impl.findpackagetype;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.report.PlaneStringReport;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriterFactory;

import java.util.ArrayList;

/**
 * Command to find package type by name.
 */
@RequiredArgsConstructor
public class FindPackageTypeCommand implements Command {

    private final FindPackageTypeContext context;
    private final PackageTypeRepository packageTypeRepository;
    private final ReportWriterFactory reportWriterFactory;

    @Override
    public void execute() {
        var result = new ArrayList<PackageType>();

        if (context.getName() == null || context.getName().isEmpty()) {
            result.addAll(packageTypeRepository.findAll());
        }
        else {
            var packageType = packageTypeRepository.find(context.getName());

            if (packageType == null) {
                return;
            }

            result.add(packageType);
        }

        var report = new PlaneStringReport();
        for (PackageType packageType : result) {
            report.addReportString(packageType.toString());
        }

        var reportWriter = reportWriterFactory.createReportWriter(context.getReportOutputChannelType(), null);

        if (reportWriter != null) {
            reportWriter.writeReport(report);
        }

        context.setResult(result);
    }
}
