package ru.hofftech.consolepackages.service.report.outputchannel.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.report.PlaneStringReport;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of {@link ReportWriter} that writes reports to a file.
 * <p>
 * This class provides functionality to write a {@link PlaneStringReport} to a specified file.
 * It ensures that the report is not null or empty before writing it. The report is written
 * to a file with a timestamped filename in the "reports" directory.
 * </p>
 * <p>
 * The filename is generated using the provided outputFileName, by appending a timestamp,
 * and preserving the original file extension.
 * </p>
 *
 * @see ReportWriter
 */
@Slf4j
@RequiredArgsConstructor
public class ReportToFileWriter implements ReportWriter {
    private final static String FOLDER_NAME = "reports";

    private final String outputFileName;

    /**
     * Implementation of {@link ReportWriter} that writes reports to a file.
     *
     * <p>This class provides functionality to write a {@link PlaneStringReport} to a specified file.
     * It ensures that the report is not null or empty before writing it. The report is written
     * to a file with a timestamped filename in the "reports" directory.</p>
     *
     * <p>The filename is generated using the provided outputFileName, by appending a timestamp,
     * and preserving the original file extension.</p>
     *
     * @see ReportWriter
     */
    @Override
    public void writeReport(PlaneStringReport report) {
        if (report == null || report.getReportStrings().isEmpty()) {
            throw new IllegalArgumentException("Report is empty or null");
        }

        var fileName = String.format(
                "%s/%s_%s.%s",
                FOLDER_NAME,
                receiveFileName(),
                new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()),
                receiveFileExtension());

        writeReportToFile(report, fileName);
    }

    private String receiveFileExtension() {
        return outputFileName.substring(outputFileName.lastIndexOf(".") + 1).toLowerCase();
    }

    private String receiveFileName() {
        return outputFileName.substring(0, outputFileName.lastIndexOf(".")).toLowerCase();
    }

    private void writeReportToFile(PlaneStringReport report, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            createReportsFolder();
            for (var reportString : report.getReportStrings()) {
                writer.write(reportString);
            }
            log.info("Report was successfully written to {}", fileName);
        } catch (IOException e) {
            log.error("Error while writing file", e);
        }
    }

    private static void createReportsFolder() {
        var directory = new File(FOLDER_NAME);

        if (directory.exists()) {
            return;
        }

        if (!directory.mkdir()) {
            throw new RuntimeException("Error while creating directory");
        }
    }
}
