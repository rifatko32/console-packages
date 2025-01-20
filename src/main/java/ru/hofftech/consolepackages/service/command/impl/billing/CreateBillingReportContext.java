package ru.hofftech.consolepackages.service.command.impl.billing;

import ch.qos.logback.core.util.StringUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.service.command.CommandContextWithResult;
import ru.hofftech.consolepackages.service.report.PlaneStringReport;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class CreateBillingReportContext extends CommandContextWithResult<String> {
    private final String userid;
    private final Date fromDate;
    private final Date toDate;

    public static class Builder {
        private String userid;
        private Date fromDate;
        private Date toDate;

        public Builder userid(String userid) {
            this.userid = userid;
            return this;
        }

        public Builder fromDate(Date fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        public Builder toDate(Date toDate) {
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
