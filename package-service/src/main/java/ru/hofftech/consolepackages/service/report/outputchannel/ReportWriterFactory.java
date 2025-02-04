package ru.hofftech.consolepackages.service.report.outputchannel;

import ru.hofftech.consolepackages.service.report.outputchannel.impl.ReportToFileWriter;

public class ReportWriterFactory {
    public ReportWriter createReportWriter(ReportOutputChannelType reportOutputChannelType, String outputFileName) {
        return switch (reportOutputChannelType) {
            case JSON_FILE, TXT_FILE -> new ReportToFileWriter(outputFileName);
            case TG_BOT, NONE -> null;
        };
    }
}
