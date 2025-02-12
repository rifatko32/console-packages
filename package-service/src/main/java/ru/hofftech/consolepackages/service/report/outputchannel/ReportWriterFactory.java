package ru.hofftech.consolepackages.service.report.outputchannel;

import ru.hofftech.consolepackages.service.report.outputchannel.impl.ReportToFileWriter;

/**
 * Factory for creating report writers.
 *
 * @see ReportWriter
 */
public class ReportWriterFactory {

    /**
     * Creates a report writer based on the specified type and output file name.
     *
     * @param reportOutputChannelType type of the report output channel
     * @param outputFileName          name of the file to write the report to
     * @return the report writer
     */
    public ReportWriter createReportWriter(ReportOutputChannelType reportOutputChannelType, String outputFileName) {
        return switch (reportOutputChannelType) {
            case JSON_FILE, TXT_FILE -> new ReportToFileWriter(outputFileName);
            case TG_BOT, NONE -> null;
        };
    }
}
