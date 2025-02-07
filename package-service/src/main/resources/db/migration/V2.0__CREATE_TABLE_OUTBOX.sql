CREATE TYPE message_status AS ENUM('PENDING', 'PROCESSED');

CREATE TABLE outbox_message
(
    id             int8             GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
    aggregate_id   VARCHAR(255)     NOT NULL,                   -- Идентификатор агрегата
    payload        JSONB            NOT NULL,                   -- Данные события
    status         message_status   NOT NULL,                   -- Статус обработки ("PENDING", "PROCESSED")
    created_at     TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    published_at   TIMESTAMP,
        CONSTRAINT billing_order_pk PRIMARY KEY (id)
);
