package ru.hofftech.consolepackages.service.report.billing.model;

import java.math.BigDecimal;

public record BillingOrderSummary(Integer packageQty, BigDecimal amount, Integer truckIdCount) {
}
