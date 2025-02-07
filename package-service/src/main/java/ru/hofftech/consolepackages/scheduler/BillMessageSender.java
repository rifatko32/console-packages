package ru.hofftech.consolepackages.scheduler;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import ru.hofftech.consolepackages.datastorage.model.entity.OutboxMessageStatus;
import ru.hofftech.consolepackages.model.dto.billing.CreatePackageBillRequest;
import ru.hofftech.consolepackages.service.outbox.OutboxMessageService;
import ru.hofftech.consolepackages.stream.BillingStreamer;

import java.sql.Timestamp;
import java.time.Clock;

@Slf4j
@RequiredArgsConstructor
public class BillMessageSender {

    private final OutboxMessageService packageBillingService;
    private final BillingStreamer billingStreamer;
    private final Clock clock;
    private final Gson gson;

    @Scheduled(fixedDelayString = "${spring.scheduler.bill-sender.fixedDelay}")
    void run() {
        var messages = packageBillingService.readUnsendMessages();

        for (var message : messages) {
            try {
                var streamMessage = gson.fromJson(message.getPayload(), CreatePackageBillRequest.class);
                billingStreamer.publish(streamMessage);

                message.setStatus(OutboxMessageStatus.PROCESSED);
                message.setPublishedAt(Timestamp.from(clock.instant()));
                packageBillingService.updateMessage(message);

            } catch (Exception e) {
                log.error("Error while sending message", e);
            }
        }
    }
}
