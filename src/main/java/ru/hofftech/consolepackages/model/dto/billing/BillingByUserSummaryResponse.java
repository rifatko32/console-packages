package ru.hofftech.consolepackages.model.dto.billing;

import ru.hofftech.consolepackages.datastorage.model.entity.OperationType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BillingByUserSummaryResponse(
    LocalDate date,
    OperationType operationType,
    Long truckCount,
    Integer packageCount,
    BigDecimal amount) {
}
