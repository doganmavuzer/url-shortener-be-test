package com.github.vivyteam.exceptions;

/*
I have created my own exception classes because then we can add some additional data for using on the front-end side.
 */
public class UrlNotValidException extends RuntimeException {
    String message;

    public UrlNotValidException(String message) {
        this.message = message;
    }

    public UrlNotValidException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public UrlNotValidException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public UrlNotValidException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public UrlNotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
    }
}
