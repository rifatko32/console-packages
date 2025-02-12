package ru.hofftech.billing.datastorage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hofftech.billing.model.entity.InboxMessage;
import ru.hofftech.billing.model.entity.InboxMessageStatus;

import java.util.List;

/**
 * Repository to work with inbox messages.
 * <p>
 * This repository provides methods to receive inbox messages by status.
 * </p>
 */
@Repository
public interface InboxMessageRepository extends JpaRepository<InboxMessage, Long> {

    /**
     * Retrieves inbox messages by status.
     *
     * @param status the status of the message
     * @return list of inbox messages
     */
    List<InboxMessage> findAllByStatus(InboxMessageStatus status);
}
