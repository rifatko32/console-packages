package ru.hofftech.consolepackages.datastorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hofftech.consolepackages.datastorage.model.entity.OutboxMessage;
import ru.hofftech.consolepackages.datastorage.model.entity.OutboxMessageStatus;

import java.util.List;

@Repository
public interface OutboxMessageRepository extends JpaRepository<OutboxMessage, Long> {

    List<OutboxMessage> findAllByStatus(OutboxMessageStatus status);
}
