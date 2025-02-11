package ru.hofftech.consolepackages.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hofftech.consolepackages.service.command.CommandReader;
import ru.hofftech.consolepackages.telegram.PackageTelegramBot;

@Configuration
@RequiredArgsConstructor
public class TelegramBotConfig {

    private final CommandReader commandReader;

    @Bean
    public PackageTelegramBot packageTelegramBot() {
        return new PackageTelegramBot(commandReader);
    }
}
