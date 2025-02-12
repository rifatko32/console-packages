package ru.hofftech.consolepackages.service.outbox;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.mapper.billing.BillingMapper;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.model.dto.billing.CreatePackageBillRequest;
import ru.hofftech.consolepackages.model.entity.OperationType;
import ru.hofftech.consolepackages.model.entity.OutboxMessage;
import ru.hofftech.consolepackages.model.entity.OutboxMessageStatus;
import ru.hofftech.consolepackages.repository.OutboxMessageRepository;

import java.util.List;

/**
 * Service for managing outbox messages related to package operations.
 * <p>
 * This service creates outbox messages for package operations and handles them by sending them to the billing service and changing their status to PROCESSED.
 * </p>
 */
@Slf4j
@RequiredArgsConstructor
public class OutboxMessageServiceImpl implements OutboxMessageService {

    private final BillingMapper billingMapper;
    private final OutboxMessageRepository outboxMessageRepository;
    private final OutboxMessageHandler outboxMessageHandler;

    /**
     * Creates an outbox message for a list of trucks and a specified client with the corresponding operation type.
     *
     * @param trucks        list of trucks to create the message for
     * @param clientId      client ID to associate with the message
     * @param operationType type of operation (LOAD or UNLOAD)
     */
    @Override
    public void createOutboxMessage(List<Truck> trucks, String clientId, OperationType operationType) {

        var message = CreatePackageBillRequest.builder()
                .clientId(clientId)
                .operationType(operationType)
                .packageBillDtos(billingMapper.mapTrucksToBillDtos(trucks))
                .build();

        outboxMessageRepository.save(
                OutboxMessage.builder()
                        .aggregateId(clientId)
                        .payload(message)
                        .status(OutboxMessageStatus.PENDING)
                        .build()
        );
    }

    /**
     * Handles all pending outbox messages by sending them to the billing service and changing their status to PROCESSED.
     */
    @Override
    public void handleOutboxMessages() {
        var messages = outboxMessageRepository.findAllByStatus(OutboxMessageStatus.PENDING);

        for (var message : messages) {
            outboxMessageHandler.handleMessage(message);
        }
    }
}
