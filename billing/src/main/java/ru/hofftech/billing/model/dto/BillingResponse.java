package ru.hofftech.billing.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import ru.hofftech.billing.model.entity.OperationType;
import ru.hofftech.billing.utils.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record BillingResponse(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    LocalDate date,
    OperationType operationType,
    Long truckCount,
    Integer packageCount,
    BigDecimal amount) {
}
