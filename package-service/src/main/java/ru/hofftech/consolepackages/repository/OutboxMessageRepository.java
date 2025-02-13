package ru.hofftech.consolepackages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hofftech.consolepackages.model.entity.OutboxMessage;
import ru.hofftech.consolepackages.model.entity.OutboxMessageStatus;

import java.util.List;

/**
 * Repository interface for managing OutboxMessage entities.
 * <p>
 * This repository provides methods for CRUD operations and
 * custom queries related to OutboxMessage entities.
 * </p>
 *
 * @see OutboxMessage
 * @see OutboxMessageStatus
 */
@Repository
public interface OutboxMessageRepository extends JpaRepository<OutboxMessage, Long> {

    /**
     * Finds all outbox messages with the given status.
     *
     * @param status the status of the messages to find
     * @return a list of outbox messages with the given status
     */
    List<OutboxMessage> findAllByStatus(OutboxMessageStatus status);
}
