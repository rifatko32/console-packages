package ru.hofftech.consolepackages.service.report.outputchannel.impl;

import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.service.report.PackagePlaceStringReport;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class ReportToJsonFileWriter implements ReportWriter {
    private final static String FILE_NAME_TEMPLATE = "report";
    private final static String FILE_EXTENSION = ".json";
    private final static String FOLDER_NAME = "reports";

    private void writeReportToFile(PackagePlaceStringReport report) {

        var fileName = String.format(
                "%s/%s_%s%s",
                FOLDER_NAME,
                FILE_NAME_TEMPLATE,
                new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()),
                FILE_EXTENSION);

        try (FileWriter writer = new FileWriter(fileName)) {
            createReportsFolder();
            writer.write(report.getReportStrings().getFirst());
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

    @Override
    public void writeReport(PackagePlaceStringReport report) {
        if (report == null || report.getReportStrings().isEmpty()) {
            throw new IllegalArgumentException("Report is empty or null");
        }

        writeReportToFile(report);
    }
}
