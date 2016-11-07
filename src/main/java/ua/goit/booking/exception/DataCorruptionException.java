package ua.goit.booking.exception;

public class DataCorruptionException extends Exception {
    public DataCorruptionException() {
    }

    public DataCorruptionException(String message) {
        super(message);
    }

    public DataCorruptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataCorruptionException(Throwable cause) {
        super(cause);
    }

}
