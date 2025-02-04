package ru.hofftech.consolepackages.service.command.impl.billing;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.service.command.CommandContextWithResult;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@Builder
public class CreateBillingReportContext extends CommandContextWithResult<String> {
    @NotEmpty
    private final String userid;
    private final LocalDate fromDate;
    private final LocalDate toDate;
}
