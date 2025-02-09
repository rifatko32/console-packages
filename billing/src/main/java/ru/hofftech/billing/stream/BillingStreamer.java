package ru.hofftech.billing.stream;

import lombok.RequiredArgsConstructor;
import ru.hofftech.billing.model.dto.CreatePackageBillRequest;
import ru.hofftech.billing.service.InboxMessageService;

@RequiredArgsConstructor
public class BillingStreamer {
    
    private final InboxMessageService inboxMessageService;

    public void handle(CreatePackageBillRequest request) {
        inboxMessageService.createInboxMessage(request);
    }
}
