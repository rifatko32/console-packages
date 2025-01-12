package ru.hofftech.consolepackages.telegram;

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

    private final static String TG_API_TOKEN = "7997289513:AAHM6eRU62wiJmzGxDaTmVlmPVjBiCki2qw";

    private final CommandReader commandReader;
    private final TelegramClient telegramClient;

    public PackageTelegramBot(CommandReader commandReader) {
        this.commandReader = commandReader;
        telegramClient = new OkHttpTelegramClient(TG_API_TOKEN);
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

            if (result == null || result.isEmpty()) {
                log.info("Command without result");
                return;
            }

            long chat_id = update.getMessage().getChatId();

            SendMessage message = SendMessage // Create a message object
                    .builder()
                    .chatId(chat_id)
                    .text(result)
                    .build();
            try {
                telegramClient.execute(message); // Sending our message object to user
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
     *
     * @param commandReader the command reader to use
     */
    public static void registerTelegramBot(CommandReader commandReader) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
                    botsApplication.registerBot(TG_API_TOKEN, new PackageTelegramBot(commandReader));
                    log.info("Telegram bot registered");
                    Thread.currentThread().join();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
