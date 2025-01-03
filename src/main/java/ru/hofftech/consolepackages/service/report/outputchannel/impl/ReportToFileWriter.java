package ru.hofftech.consolepackages.service.report.outputchannel.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class ReportToFileWriter implements ReportWriter {
    private final static String FILE_NAME_TEMPLATE = "report";
    private final static String FOLDER_NAME = "reports";

    private final String fileExtension;

    @Override
    public void writeReport(PackagePlaceStringReport report) {
        if (report == null || report.getReportStrings().isEmpty()) {
            throw new IllegalArgumentException("Report is empty or null");
        }

        var fileName = String.format(
                "%s/%s_%s.%s",
                FOLDER_NAME,
                FILE_NAME_TEMPLATE,
                new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()),
                fileExtension);

        writeReportToFile(report, fileName);
    }

    private void writeReportToFile(PackagePlaceStringReport report, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            createReportsFolder();
            for(var reportString : report.getReportStrings()) {
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
