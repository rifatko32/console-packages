package ru.hofftech.consolepackages.service.outbox;

import ru.hofftech.consolepackages.model.entity.OperationType;
import ru.hofftech.consolepackages.model.Truck;

import java.util.List;

/**
 * Service for managing outbox messages related to package operations.
 */
public interface OutboxMessageService {

    /**
     * Creates an outbox message for a list of trucks and a specified client with the corresponding operation type.
     *
     * @param trucks      list of trucks to create the message for
     * @param clientId    client ID to associate with the message
     * @param operationType type of operation (LOAD or UNLOAD)
     */
    void createOutboxMessage(List<Truck> trucks, String clientId, OperationType operationType);

    /**
     * Handles all pending outbox messages by sending them to the billing service and changing their status to PROCESSED.
     */
    void handleOutboxMessages();
}
