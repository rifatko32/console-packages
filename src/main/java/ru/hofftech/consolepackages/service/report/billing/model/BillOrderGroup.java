package ru.hofftech.consolepackages.service.report.billing.model;

import ru.hofftech.consolepackages.datastorage.model.entity.OperationType;

import java.time.LocalDate;

public record BillOrderGroup(LocalDate orderDate, OperationType operationType) {
}
