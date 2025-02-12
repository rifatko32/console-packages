package ru.hofftech.consolepackages.service.outbox;

import ru.hofftech.consolepackages.model.entity.OutboxMessage;

/**
 * Interface for handling outbox messages.
 * <p>
 * Implementations of this interface are responsible for processing outbox messages,
 * which may include tasks such as updating their status and sending them to external services.
 * </p>
 */
public interface OutboxMessageHandler {

    /**
     * Handles the processing of an outbox message.
     * <p>
     * Implementations should update the status of the message and perform
     * necessary actions such as sending the message to external services.
     * </p>
     *
     * @param message the outbox message to be processed
     */
    void handleMessage(OutboxMessage message);
}
