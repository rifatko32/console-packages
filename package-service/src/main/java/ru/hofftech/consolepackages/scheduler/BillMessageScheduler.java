package ru.hofftech.consolepackages.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.service.outbox.OutboxMessageService;

/**
 * Periodically sends messages from the outbox to the billing service.
 * <p>
 * The period is configured via the {@code spring.scheduler.bill-sender.fixedDelay}
 * property.
 * </p>
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BillMessageScheduler {

    private final OutboxMessageService outboxMessageService;

    /**
     * Executes the scheduled task to send messages from the outbox to the billing service.
     * <p>
     * This method is triggered periodically based on the fixed delay defined in the
     * {@code spring.scheduler.bill-sender.fixedDelay} property. It logs the start of
     * the task and delegates the handling of outbox messages to the {@link OutboxMessageService}.
     * </p>
     */
    @Scheduled(fixedDelayString = "${spring.scheduler.bill-sender.fixedDelay}")
    void run() {
        log.info("Sending bill messages...");
        outboxMessageService.handleOutboxMessages();
    }
}
