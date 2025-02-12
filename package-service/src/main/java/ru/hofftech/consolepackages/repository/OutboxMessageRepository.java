package ru.hofftech.consolepackages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hofftech.consolepackages.model.entity.OutboxMessage;
import ru.hofftech.consolepackages.model.entity.OutboxMessageStatus;

import java.util.List;

@Repository
public interface OutboxMessageRepository extends JpaRepository<OutboxMessage, Long> {

    List<OutboxMessage> findAllByStatus(OutboxMessageStatus status);
}
