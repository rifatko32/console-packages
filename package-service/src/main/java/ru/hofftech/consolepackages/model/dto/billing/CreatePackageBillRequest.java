package ru.hofftech.consolepackages.model.dto.billing;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import ru.hofftech.consolepackages.datastorage.model.entity.OperationType;

import java.util.List;

@Builder
public record CreatePackageBillRequest(
        @NotNull
        Long requestId,
        @NotNull
        String clientId,
        @NotNull
        OperationType operationType,
        @NotNull
        List<PackageBillDto> packageBillDtos) {
}
