package ru.hofftech.consolepackages.exception;

/**
 * Exception that is thrown when creating a truck failed.
 * <p>
 * This exception is used to indicate that the creation of a truck
 * failed due to some reason.
 * </p>
 */
public class TruckCreatingException extends RuntimeException{

    public TruckCreatingException(Throwable cause) {
        super(cause);
    }
}
