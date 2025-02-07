package ru.hofftech.consolepackages.service.outbox;

import ru.hofftech.consolepackages.datastorage.model.entity.OperationType;
import ru.hofftech.consolepackages.datastorage.model.entity.OutboxMessage;
import ru.hofftech.consolepackages.model.Truck;

import java.util.List;

public interface OutboxMessageService {

    void createOutboxMessage(List<Truck> trucks, String clientId, OperationType operationType);

    List<OutboxMessage> readUnsendMessages();

    void updateMessage(OutboxMessage message);
}
