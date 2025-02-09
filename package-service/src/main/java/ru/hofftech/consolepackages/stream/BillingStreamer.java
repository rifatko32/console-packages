package ru.hofftech.consolepackages.stream;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import ru.hofftech.consolepackages.model.dto.billing.CreatePackageBillRequest;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class BillingStreamer {

    private final StreamBridge streamBridge;

    static final String BILLING_REQUEST = "biiling-out-0";

    public void publish(CreatePackageBillRequest billRequest){
        var message = MessageBuilder.withPayload(billRequest)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();

        streamBridge.send(BILLING_REQUEST, message);
    }
}
