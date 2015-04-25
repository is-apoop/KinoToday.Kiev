package kino.today.parsers;

import java.io.IOException;

/**
 * Created by hlib on 4/25/15.
 */
public class CinemaUpdateFailedException extends Exception {
    public CinemaUpdateFailedException(Throwable e) {
    }

    public CinemaUpdateFailedException() {
        super();
    }

    public CinemaUpdateFailedException(String message) {
        super(message);
    }

    public CinemaUpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    protected CinemaUpdateFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
