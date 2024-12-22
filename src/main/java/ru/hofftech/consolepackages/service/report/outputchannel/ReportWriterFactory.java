package ru.hofftech.consolepackages.service.report.outputchannel;

import ru.hofftech.consolepackages.service.report.outputchannel.impl.ReportToConsoleWriter;
import ru.hofftech.consolepackages.service.report.outputchannel.impl.ReportToJsonFileWriter;

public class ReportWriterFactory {
    public ReportWriter createReportWriter(ReportOutputChannelType reportOutputChannelType) {
        return switch (reportOutputChannelType) {
            case CONSOLE -> new ReportToConsoleWriter();
            case JSONFILE -> new ReportToJsonFileWriter();
            default ->
                    throw new IllegalArgumentException("Unknown report output channel type: " + reportOutputChannelType.name());
        };
    }
}
