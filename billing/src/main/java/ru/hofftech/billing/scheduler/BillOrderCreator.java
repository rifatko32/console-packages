package ru.hofftech.billing.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.hofftech.billing.service.InboxMessageService;

@Slf4j
@RequiredArgsConstructor
@Service
public class BillOrderCreator {

    private final InboxMessageService inboxMessageService;

    @Scheduled(fixedDelayString = "${spring.scheduler.inbox-messages.fixedDelay}")
    void run() {
        log.info("Processing inbox messages...");
        inboxMessageService.handleInboxMessages();
    }
}
