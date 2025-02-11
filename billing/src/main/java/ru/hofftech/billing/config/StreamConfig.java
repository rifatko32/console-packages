package ru.hofftech.billing.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import ru.hofftech.billing.datastorage.InboxMessageRepository;
import ru.hofftech.billing.model.dto.CreatePackageBillRequest;
import ru.hofftech.billing.service.InboxMessageService;
import ru.hofftech.billing.service.InboxMessageServiceImpl;
import ru.hofftech.billing.service.PackageBillingService;

import java.time.Clock;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class StreamConfig {

    private final InboxMessageRepository inboxMessageRepository;
    private final PackageBillingService packageBillingService;
    private final Clock clock;

    @Bean
    public Consumer<Message<CreatePackageBillRequest>> billing() {
        return message -> inboxMessageService().createInboxMessage(message.getPayload());
    }

    @Bean
    public InboxMessageService inboxMessageService() {
        return new InboxMessageServiceImpl(
                inboxMessageRepository,
                packageBillingService,
                clock);
    }
}
