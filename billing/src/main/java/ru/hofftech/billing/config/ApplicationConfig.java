package ru.hofftech.billing.config;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.hofftech.billing.datastorage.BillingOrderRepository;
import ru.hofftech.billing.datastorage.InboxMessageRepository;
import ru.hofftech.billing.model.dto.CreatePackageBillRequest;
import ru.hofftech.billing.service.InboxMessageService;
import ru.hofftech.billing.service.InboxMessageServiceImpl;
import ru.hofftech.billing.service.PackageBillingService;
import ru.hofftech.billing.service.PackageBillingServiceImpl;
import ru.hofftech.billing.stream.BillingStreamer;

import java.time.Clock;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
@EnableScheduling
public class ApplicationConfig {

    private final BillingOrderRepository billingOrderRepository;
    private final InboxMessageRepository inboxMessageRepository;

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public PackageBillingService packageBillingService() {
        return new PackageBillingServiceImpl(
                billingOrderRepository,
                clock());
    }

    @Bean
    public InboxMessageService inboxMessageService() {
        return new InboxMessageServiceImpl(
                inboxMessageRepository,
                packageBillingService(),
                gson(),
                clock());
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public BillingStreamer billingStreamer() {
        return new BillingStreamer(inboxMessageService());
    }

    @Bean
    public Consumer<Message<CreatePackageBillRequest>> billingConsumer() {
        return message -> billingStreamer().handle(message.getPayload());
    }
}
