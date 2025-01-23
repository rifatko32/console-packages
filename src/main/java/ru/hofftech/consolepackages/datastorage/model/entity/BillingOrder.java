package ru.hofftech.consolepackages.datastorage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import static ru.hofftech.consolepackages.util.DateUtils.DATE_FORMAT;

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

    public Date getDateWithoutTimeUsingFormat() {
        var formatter = new SimpleDateFormat(DATE_FORMAT);
        try {
            return formatter.parse(formatter.format(new Date()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
