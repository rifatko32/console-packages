package ru.hofftech.consolepackages.service.outbox;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import ru.hofftech.consolepackages.model.entity.OutboxMessage;
import ru.hofftech.consolepackages.model.entity.OutboxMessageStatus;
import ru.hofftech.consolepackages.repository.OutboxMessageRepository;
import ru.hofftech.consolepackages.stream.BillingStreamer;

import java.sql.Timestamp;
import java.time.Clock;

/**
 * Implementation of the {@link OutboxMessageHandler} interface. It handles outbox messages by sending them to the billing service
 * and changing their status to PROCESSED.
 */
@Slf4j
@RequiredArgsConstructor
public class OutboxMessageHandlerImpl implements OutboxMessageHandler {

    private final OutboxMessageRepository outboxMessageRepository;
    private final BillingStreamer billingStreamer;
    private final Clock clock;

    /**
     * Implementation of the {@link OutboxMessageHandler} interface. It handles outbox messages by sending them to the billing service
     * and changing their status to PROCESSED.
     * <p>
     * This class is annotated with {@link Transactional} and {@link Retryable} to ensure that the message is processed in a transaction and
     * if an exception occurs during processing, the method will be retried with a backoff.
     * </p>
     */
    @Transactional
    @Retryable(backoff = @Backoff(delay = 500))
    public void handleMessage(OutboxMessage message) {
        try {
            updateMessageStatusToProcessed(message);
            billingStreamer.publish(message.getPayload());
        } catch (Exception e) {
            log.error("Error while sending message", e);
        }
    }

    private void updateMessageStatusToProcessed(OutboxMessage message) {
        message.setStatus(OutboxMessageStatus.PROCESSED);
        message.setPublishedAt(Timestamp.from(clock.instant()));
        outboxMessageRepository.save(message);
    }
}
