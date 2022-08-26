package by.semashko.greenjokerquestbot.exception;

public class InvalidUrlException extends RuntimeException {

    public InvalidUrlException() {
    }

    public InvalidUrlException(String message) {
        super(message);
    }

    public InvalidUrlException(String message, Throwable cause) {
        super(message, cause);
    }
}
