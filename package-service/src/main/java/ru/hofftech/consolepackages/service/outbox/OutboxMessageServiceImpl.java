package ru.hofftech.consolepackages.service.outbox;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.datastorage.model.entity.OperationType;
import ru.hofftech.consolepackages.datastorage.model.entity.OutboxMessage;
import ru.hofftech.consolepackages.datastorage.model.entity.OutboxMessageStatus;
import ru.hofftech.consolepackages.datastorage.repository.OutboxMessageRepository;
import ru.hofftech.consolepackages.mapper.billing.BillingMapper;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.model.dto.billing.CreatePackageBillRequest;
import ru.hofftech.consolepackages.stream.BillingStreamer;

import java.sql.Timestamp;
import java.time.Clock;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class OutboxMessageServiceImpl implements OutboxMessageService {

    private final BillingMapper billingMapper;
    private final OutboxMessageRepository outboxMessageRepository;
    private final BillingStreamer billingStreamer;
    private final Gson gson;
    private final Clock clock;

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
                        .payload(gson.toJson(message))
                        .status(OutboxMessageStatus.PENDING)
                        .publishedAt(Timestamp.from(clock.instant()))
                        .build()
        );
    }

    @Override
    public void updateMessage(OutboxMessage message) {
        outboxMessageRepository.save(message);
    }

    @Override
    public void handleOutboxMessages() {
        var messages = outboxMessageRepository.findAllByStatus(OutboxMessageStatus.PENDING);

        for (var message : messages) {
            try {
                var streamMessage = gson.fromJson(message.getPayload(), CreatePackageBillRequest.class);
                billingStreamer.publish(streamMessage);

                message.setStatus(OutboxMessageStatus.PROCESSED);
                message.setPublishedAt(Timestamp.from(clock.instant()));
                updateMessage(message);

            } catch (Exception e) {
                log.error("Error while sending message", e);
            }
        }
    }
}
