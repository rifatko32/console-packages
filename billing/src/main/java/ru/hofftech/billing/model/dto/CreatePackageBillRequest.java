package ru.hofftech.billing.model.dto;

import ru.hofftech.billing.model.entity.OperationType;

import java.util.List;

public record CreatePackageBillRequest(String clientId, OperationType operationType, List<PackageBillDto> packageBillDtos) {
}
