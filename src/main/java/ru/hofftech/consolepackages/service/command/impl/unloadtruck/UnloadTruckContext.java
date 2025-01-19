package ru.hofftech.consolepackages.service.command.impl.unloadtruck;

import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.report.ReportEngineType;
import ru.hofftech.consolepackages.service.report.outputchannel.ReportOutputChannelType;

public record UnloadTruckContext(
        String inFilePath,
        ReportEngineType reportEngineType,
        ReportOutputChannelType reportOutputChannelType,
        String outFilePath,
        boolean withCount,
        String clientId) implements CommandContext {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String inFilePath;
        private ReportEngineType reportEngineType;
        private ReportOutputChannelType reportOutputChannelType;
        private String outFilePath;
        private boolean withCount;
        private String clientId;

        public Builder inFilePath(String inFilePath) {
            this.inFilePath = inFilePath;
            return this;
        }

        public Builder reportEngineType(ReportEngineType reportEngineType) {
            this.reportEngineType = reportEngineType;
            return this;
        }

        public Builder reportOutputChannelType(ReportOutputChannelType reportOutputChannelType) {
            this.reportOutputChannelType = reportOutputChannelType;
            return this;
        }

        public Builder outFilePath(String outFilePath) {
            this.outFilePath = outFilePath;
            return this;
        }

        public Builder withCount(boolean withCount) {
            this.withCount = withCount;
            return this;
        }

        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public UnloadTruckContext build() {
            return new UnloadTruckContext(
                    inFilePath,
                    reportEngineType,
                    reportOutputChannelType,
                    outFilePath,
                    withCount,
                    clientId
            );
        }
    }
}
