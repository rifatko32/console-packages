package ru.hofftech.consolepackages.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hofftech.consolepackages.stream.BillingStreamer;

@Configuration
@RequiredArgsConstructor
public class SBillingStreamerConfig {

    private final StreamBridge streamBridge;

    @Bean
    public BillingStreamer billingStreamer() {
        return new BillingStreamer(streamBridge);
    }
}
