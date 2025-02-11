package ru.hofftech.consolepackages.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.hofftech.consolepackages.service.outbox.OutboxMessageService;

@Slf4j
@RequiredArgsConstructor
@Service
public class BillMessageScheduler {

    private final OutboxMessageService outboxMessageService;

    @Scheduled(fixedDelayString = "${spring.scheduler.bill-sender.fixedDelay}")
    void run() {
        log.info("Sending bill messages...");
        outboxMessageService.handleOutboxMessages();
    }
}
