package ru.hofftech.consolepackages.service.report.outputchannel;

import ru.hofftech.consolepackages.service.report.outputchannel.impl.ReportToConsoleWriter;
import ru.hofftech.consolepackages.service.report.outputchannel.impl.ReportToFileWriter;

public class ReportWriterFactory {
    public ReportWriter createReportWriter(ReportOutputChannelType reportOutputChannelType, String outputFileName) {
        return switch (reportOutputChannelType) {
            case CONSOLE -> new ReportToConsoleWriter();
            case JSONFILE, TXT_FILE -> new ReportToFileWriter(outputFileName);
            case TG_BOT -> null;
            default ->
                    throw new IllegalArgumentException("Unknown report output channel type: " + reportOutputChannelType.name());
        };
    }
}
