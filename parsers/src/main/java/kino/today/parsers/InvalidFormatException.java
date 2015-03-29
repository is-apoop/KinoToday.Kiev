package kino.today.parsers;

/**
 * Created by hlib on 29.03.15.
 */
public class InvalidFormatException extends Exception {
    public InvalidFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFormatException(String message) {
        super(message);
    }
}
