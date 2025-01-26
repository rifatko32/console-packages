package ru.hofftech.consolepackages.datastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Billing order entity.
 */
@Getter
@AllArgsConstructor
@Builder
public class BillingOrder {

    private final String id = UUID.randomUUID().toString();

    @Setter
    private String clientId;
    @Setter
    private LocalDate orderDate;
    @Setter
    private BigDecimal amount;
    @Setter
    private Integer packageQty;
    @Setter
    private UUID truckId;
    @Setter
    private String comment;
    @Setter
    private OperationType operationType;
}
