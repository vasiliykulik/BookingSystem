package ua.goit.booking.exception;

public class OperationSuccessException extends RuntimeException {
    public OperationSuccessException() {
        super();
    }

    public OperationSuccessException(String message) {
        super(message);
    }

    public OperationSuccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationSuccessException(Throwable cause) {
        super(cause);
    }
}
