package ru.hofftech.consolepackages.datastorage.model.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class OutboxMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aggregateId;
    private String payload;
    @Enumerated(EnumType.STRING)
    private OutboxMessageStatus status;
    private Timestamp createdAt;
    private Timestamp publishedAt;
}