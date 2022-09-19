package com.github.vivyteam.exceptions;
/*
I have created my own exception classes because then we can add some additional data for using on the front-end side.
 */
public class UrlNotFoundException extends RuntimeException{
    public UrlNotFoundException() {
    }

    public UrlNotFoundException(String message) {
        super(message);
    }

    public UrlNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UrlNotFoundException(Throwable cause) {
        super(cause);
    }

    public UrlNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
