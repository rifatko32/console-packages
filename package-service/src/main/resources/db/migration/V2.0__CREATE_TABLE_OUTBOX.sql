CREATE TABLE outbox_message
(
    id             SERIAL           PRIMARY KEY,
    aggregate_id   VARCHAR(255)     NOT NULL,                   -- Идентификатор агрегата
    payload        JSONB            NOT NULL,                   -- Данные события
    status         VARCHAR(30)      NOT NULL,                   -- Статус обработки ("PENDING", "PROCESSED")
    created_at     TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    published_at   TIMESTAMP
);
