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

import java.sql.Timestamp;
import java.time.Clock;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class OutboxMessageServiceImpl implements OutboxMessageService {

    private final BillingMapper billingMapper;
    private final OutboxMessageRepository outboxMessageRepository;
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
    public List<OutboxMessage> readUnsendMessages() {
        return outboxMessageRepository.findAllByStatus(OutboxMessageStatus.PENDING);
    }

    @Override
    public void updateMessage(OutboxMessage message) {
        outboxMessageRepository.save(message);
    }
}
