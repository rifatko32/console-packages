package ru.hofftech.billing.datastorage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hofftech.billing.model.entity.InboxMessage;
import ru.hofftech.billing.model.entity.InboxMessageStatus;

import java.util.List;

@Repository
public interface InboxMessageRepository extends JpaRepository<InboxMessage, Long> {

    List<InboxMessage> findAllByStatus(InboxMessageStatus status);
}
