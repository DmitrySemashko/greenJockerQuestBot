package by.semashko.greenjokerquestbot.exception;

public class NoHandlerFoundException extends RuntimeException {

    public NoHandlerFoundException() {

    }

    public NoHandlerFoundException(String message) {
        super(message);
    }

    public NoHandlerFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoHandlerFoundException(Throwable cause) {
        super(cause);
    }
}
