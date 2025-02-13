package ru.hofftech.billing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.hofftech.billing.datastorage.InboxMessageRepository;
import ru.hofftech.billing.model.dto.CreatePackageBillRequest;
import ru.hofftech.billing.model.entity.InboxMessage;
import ru.hofftech.billing.model.entity.InboxMessageStatus;

import java.sql.Timestamp;
import java.time.Clock;

/**
 * Service implementation for handling inbox messages.
 * <p>
 * This service provides methods to process and create inbox messages.
 * </p>
 */
@RequiredArgsConstructor
public class InboxMessageServiceImpl implements InboxMessageService {

    private final InboxMessageRepository inboxMessageRepository;
    private final PackageBillingService packageBillingService;
    private final Clock clock;

    /**
     * Processes all pending inbox messages.
     * <p>
     * This method retrieves all inbox messages with a status of PENDING from the repository,
     * processes each message by creating a package bill using the payload, updates the status
     * to PROCESSED, sets the published timestamp, and saves the changes back to the repository.
     * The method is transactional to ensure atomicity of operations.
     * </p>
     */
    @Override
    @Transactional
    public void handleInboxMessages() {
        var messages = inboxMessageRepository.findAllByStatus(InboxMessageStatus.PENDING);

        messages.forEach(message -> {
            packageBillingService.creatPackageBill(message.getPayload());
            message.setStatus(InboxMessageStatus.PROCESSED);
            message.setPublishedAt(Timestamp.from(clock.instant()));
            inboxMessageRepository.save(message);
        });

    }

    /**
     * Creates a new inbox message from the given request.
     * <p>
     * This method creates a new inbox message with the given request and saves it to the database.
     * </p>
     *
     * @param request the request to create the inbox message
     */
    @Override
    public void createInboxMessage(CreatePackageBillRequest request) {
        var inboxMessage = InboxMessage.builder()
                .aggregateId(request.clientId())
                .status(InboxMessageStatus.PENDING)
                .payload(request)
                .build();

        inboxMessageRepository.save(inboxMessage);
    }
}
