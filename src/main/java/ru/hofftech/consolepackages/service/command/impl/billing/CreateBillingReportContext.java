package ru.hofftech.consolepackages.service.command.impl.billing;

import ch.qos.logback.core.util.StringUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.service.command.CommandContextWithResult;
import ru.hofftech.consolepackages.service.report.PlaneStringReport;

import java.time.LocalDate;
import java.util.Date;

@Getter
@RequiredArgsConstructor
public class CreateBillingReportContext extends CommandContextWithResult<String> {
    private final String userid;
    private final LocalDate fromDate;
    private final LocalDate toDate;

    public static class Builder {
        private String userid;
        private LocalDate fromDate;
        private LocalDate toDate;

        public Builder userid(String userid) {
            this.userid = userid;
            return this;
        }

        public Builder fromDate(LocalDate fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        public Builder toDate(LocalDate toDate) {
            this.toDate = toDate;
            return this;
        }

        public CreateBillingReportContext build() {
            if (StringUtil.isNullOrEmpty(userid)) {
                throw new IllegalArgumentException("userid is null or empty");
            }

            return new CreateBillingReportContext(userid, fromDate, toDate);
        }
    }
}
