package ru.hofftech.consolepackages.telegram;

import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.hofftech.consolepackages.service.command.CommandReader;

/**
 * Telegram bot that reads commands from users and passes them to the {@link CommandReader} service.
 * <p>
 * The bot is registered using the {@link #registerTelegramBot(CommandReader)} static method.
 * </p>
 */
@Slf4j
public class PackageTelegramBot implements LongPollingSingleThreadUpdateConsumer {

    private final CommandReader commandReader;
    private final TelegramClient telegramClient;
    private final static String tgApiToken = System.getenv("TG_API_TOKEN");

    public PackageTelegramBot(CommandReader commandReader) {
        this.commandReader = commandReader;
        telegramClient = new OkHttpTelegramClient(tgApiToken);
    }

    /**
     * Processes the incoming {@link Update} by reading a command from the update's text
     * and sending the result of the command to the user.
     *
     * @param update the update to process
     */
    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var result = commandReader.readCommand(update.getMessage().getText());

            if (StringUtils.isEmpty(result)) {
                log.info("Command without result");
                return;
            }

            long chat_id = update.getMessage().getChatId();

            SendMessage message = SendMessage
                    .builder()
                    .chatId(chat_id)
                    .text(result)
                    .build();
            try {
                telegramClient.execute(message);
            } catch (TelegramApiException e) {
                log.error("Error while sending message", e);
            }
        }
    }

    /**
     * Registers the Telegram bot with the given {@link CommandReader} instance.
     * <p>
     * The registration process is done in a separate thread to avoid blocking the main application thread.
     * </p>
     */
    @PostConstruct
    public void registerTelegramBot() {
        new Thread(() -> {
            try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
                botsApplication.registerBot(tgApiToken, new PackageTelegramBot(commandReader));
                log.info("Telegram bot registered");
                Thread.currentThread().join();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
