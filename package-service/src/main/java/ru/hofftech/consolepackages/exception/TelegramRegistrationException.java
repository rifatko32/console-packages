package ru.hofftech.consolepackages.exception;

/**
 * Exception that is thrown when registration of Telegram bot failed.
 * <p>
 * This exception is used to indicate that the registration of Telegram bot
 * failed due to some reason.
 * </p>
 */
public class TelegramRegistrationException extends RuntimeException {

    public TelegramRegistrationException(Throwable cause) {
        super(cause);
    }
}
