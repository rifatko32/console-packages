package ru.hofftech.billing.service;

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
    private final Clock clock;

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
