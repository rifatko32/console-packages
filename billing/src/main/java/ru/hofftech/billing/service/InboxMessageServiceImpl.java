package ru.hofftech.billing.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.hofftech.billing.datastorage.InboxMessageRepository;
import ru.hofftech.billing.model.dto.CreatePackageBillRequest;
import ru.hofftech.billing.model.entity.InboxMessage;
import ru.hofftech.billing.model.entity.InboxMessageStatus;

import java.sql.Timestamp;
import java.time.Clock;

@RequiredArgsConstructor
public class InboxMessageServiceImpl implements InboxMessageService{

    private final InboxMessageRepository inboxMessageRepository;
    private final PackageBillingService packageBillingService;
    private final Gson gson;
    private final Clock clock;

    @Override
    @Transactional
    public void handleInboxMessages() {
        var messages = inboxMessageRepository.findAllByStatus(InboxMessageStatus.PENDING);

        messages.forEach(message -> {
            var request = gson.fromJson(message.getPayload(), CreatePackageBillRequest.class);
            packageBillingService.creatPackageBill(request);
            message.setStatus(InboxMessageStatus.PROCESSED);
            inboxMessageRepository.save(message);
        });

    }

    @Override
    public void createInboxMessage(CreatePackageBillRequest request) {
        var inboxMessage = InboxMessage.builder()
                .aggregateId(request.clientId())
                .status(InboxMessageStatus.PENDING)
                .createdAt(Timestamp.from(clock.instant()))
                .payload(gson.toJson(request))
                .build();

        inboxMessageRepository.save(inboxMessage);
    }
}
