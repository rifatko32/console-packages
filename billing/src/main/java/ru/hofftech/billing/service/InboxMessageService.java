package ru.hofftech.billing.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hofftech.billing.model.dto.CreatePackageBillRequest;

/**
 * Service interface for handling inbox messages.
 * <p>
 * This service provides methods to process and create inbox messages.
 * </p>
 */
public interface InboxMessageService {

    /**
     * Processes all inbox messages.
     * <p>
     * This method is marked as {@link Transactional} to ensure that all operations are executed in a single transaction.
     * </p>
     */
    void handleInboxMessages();

    /**
     * Creates a new inbox message from the given request.
     * <p>
     * This method creates a new inbox message with the given request and saves it to the database.
     * </p>
     *
     * @param request the request to create the inbox message
     */
    void createInboxMessage(CreatePackageBillRequest request);
}
