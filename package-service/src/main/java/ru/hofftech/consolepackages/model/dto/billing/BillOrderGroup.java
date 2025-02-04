package ru.hofftech.consolepackages.model.dto.billing;

import ru.hofftech.consolepackages.datastorage.model.entity.OperationType;

import java.time.LocalDate;

public record BillOrderGroup(LocalDate orderDate, OperationType operationType) {
}
