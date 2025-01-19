package ru.hofftech.consolepackages.datastorage.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
public class BillingOrder {

    private final String id;

    @Setter
    private String clientId;
    @Setter
    private Date orderDate;
    @Setter
    private BigDecimal amount;
    @Setter
    private Integer packageQty;
    @Setter
    private UUID truckId;
    @Setter
    private String comment;

    public BillingOrder(
            String clientId,
            Date orderDate,
            BigDecimal amount,
            Integer packageQty,
            UUID truckId,
            String comment) {
        id = UUID.randomUUID().toString();

        this.clientId = clientId;
        this.orderDate = orderDate;
        this.amount = amount;
        this.packageQty = packageQty;
        this.truckId = truckId;
        this.comment = comment;
    }
}
