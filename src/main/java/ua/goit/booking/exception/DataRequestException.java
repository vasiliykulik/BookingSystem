package ua.goit.booking.exception;

public class DataRequestException extends RuntimeException {
    public DataRequestException() {
    }

    public DataRequestException(String message) {
        super(message);
    }

    public DataRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
