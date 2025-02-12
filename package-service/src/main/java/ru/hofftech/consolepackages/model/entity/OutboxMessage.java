package ru.hofftech.consolepackages.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import ru.hofftech.consolepackages.model.dto.billing.CreatePackageBillRequest;

import java.sql.Timestamp;

/**
 * Represents a message that is waiting for sending to the billing service.
 * <p>
 * This entity contains the message content in the form of a JSON string, the
 * message status, the date and time when the message was added to the outbox,
 * and the date and time when the message was sent or processing failed.
 * </p>
 *
 * @see OutboxMessageStatus
 * @see CreatePackageBillRequest
 */
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class OutboxMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "aggregate_id", nullable = false)
    private String aggregateId;

    @Column(name = "payload", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private CreatePackageBillRequest payload;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OutboxMessageStatus status;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private Timestamp createdAt;

    @Column(name = "published_at")
    private Timestamp publishedAt;
}