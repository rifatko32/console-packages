package ru.hofftech.consolepackages.datastorage.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Setter
    private OperationType operationType;

    public BillingOrder(
            String clientId,
            Date orderDate,
            BigDecimal amount,
            Integer packageQty,
            UUID truckId,
            String comment,
            OperationType operationType) {
        id = UUID.randomUUID().toString();

        this.clientId = clientId;
        this.orderDate = orderDate;
        this.amount = amount;
        this.packageQty = packageQty;
        this.truckId = truckId;
        this.comment = comment;
        this.operationType = operationType;
    }

    public Date getDateWithoutTimeUsingFormat() {
        var formatter = new SimpleDateFormat("yyyy.MM.dd");
        try {
            return formatter.parse(formatter.format(new Date()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
