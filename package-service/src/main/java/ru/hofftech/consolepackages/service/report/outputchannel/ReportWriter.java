package ru.hofftech.consolepackages.service.report.outputchannel;

import ru.hofftech.consolepackages.service.report.PlaneStringReport;

/**
 * Interface for writing reports to output channels.
 * <p>
 * This interface provides a single method to write a report to an output channel.
 * The report is represented as a {@link PlaneStringReport}.
 * </p>
 */
public interface ReportWriter {
    /**
     * Writes a report to an output channel.
     * <p>
     * The report is represented as a {@link PlaneStringReport} and will be
     * written to a specified output channel, such as a file or console.
     * Implementations should ensure that the report is not null or empty
     * before attempting to write it.
     * </p>
     *
     * @param report the {@link PlaneStringReport} to be written
     * @throws IllegalArgumentException if the report is null or empty
     */
    void writeReport(PlaneStringReport report);
}
