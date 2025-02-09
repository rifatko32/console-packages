package ru.hofftech.billing.service;

import ru.hofftech.billing.model.dto.CreatePackageBillRequest;

public interface InboxMessageService {

    void handleInboxMessages();

    void createInboxMessage(CreatePackageBillRequest request);
}
