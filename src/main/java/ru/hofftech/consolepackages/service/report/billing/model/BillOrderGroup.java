package ru.hofftech.consolepackages.service.report.billing.model;

import ru.hofftech.consolepackages.datastorage.model.entity.OperationType;

import java.util.Date;

public record BillOrderGroup(Date orderDate, OperationType operationType) {
}
