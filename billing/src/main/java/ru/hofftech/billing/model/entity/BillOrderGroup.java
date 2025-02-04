package ru.hofftech.billing.model.entity;

import java.time.LocalDate;

public record BillOrderGroup(LocalDate orderDate, OperationType operationType) {
}
