package ru.hofftech.consolepackages.model.dto.billing;

import ru.hofftech.consolepackages.model.entity.OperationType;

import java.time.LocalDate;

public record BillOrderGroup(LocalDate orderDate, OperationType operationType) {
}
