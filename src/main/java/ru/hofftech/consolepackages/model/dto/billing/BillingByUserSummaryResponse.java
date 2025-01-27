package ru.hofftech.consolepackages.model.dto.billing;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.hofftech.consolepackages.datastorage.model.entity.OperationType;
import ru.hofftech.consolepackages.util.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BillingByUserSummaryResponse(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    LocalDate date,
    OperationType operationType,
    Long truckCount,
    Integer packageCount,
    BigDecimal amount) {
}
