package ru.hofftech.billing.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Billing order entity.
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "package_qty", nullable = false)
    private Integer packageQty;

    @Column(name = "truck_id", nullable = false)
    private UUID truckId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "operation_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private OperationType operationType;
}
