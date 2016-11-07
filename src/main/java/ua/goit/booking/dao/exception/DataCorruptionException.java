package ua.goit.booking.dao.exception;

public class DataCorruptionException extends RuntimeException {
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

    public DataCorruptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
