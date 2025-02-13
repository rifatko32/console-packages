package ru.hofftech.billing.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.hofftech.billing.service.InboxMessageService;

/**
 * Schedules the processing of inbox messages.
 * <p>
 * This scheduler starts a service method every fixed delay period of time.
 * </p>
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BillingOrderScheduler {

    private final InboxMessageService inboxMessageService;

    /**
     * Starts the service to process inbox messages every fixed delay period of time.
     */
    @Scheduled(fixedDelayString = "${spring.scheduler.inbox-messages.fixedDelay}")
    void run() {
        log.info("Processing inbox messages...");
        inboxMessageService.handleInboxMessages();
    }
}
