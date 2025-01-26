package ru.hofftech.consolepackages.service.command.impl.billing;

import ch.qos.logback.core.util.StringUtil;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.service.command.CommandContextWithResult;
import ru.hofftech.consolepackages.service.report.PlaneStringReport;

import java.time.LocalDate;
import java.util.Date;

@Getter
@RequiredArgsConstructor
@Builder
public class CreateBillingReportContext extends CommandContextWithResult<String> {
    @NotEmpty
    private final String userid;
    private final LocalDate fromDate;
    private final LocalDate toDate;
}
