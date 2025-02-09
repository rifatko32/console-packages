package ru.hofftech.billing.model.dto;

import jakarta.validation.constraints.NotNull;
import ru.hofftech.billing.model.entity.OperationType;

import java.util.List;

public record CreatePackageBillRequest(
        @NotNull
        String clientId,
        @NotNull
        OperationType operationType,
        @NotNull
        List<PackageBillDto> packageBillDtos) {
}
