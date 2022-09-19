package com.github.vivyteam.exceptions;
/*
I have created my own exception classes because then we can add some additional data for using on the front-end side.
 */
public class UrlExpiredException extends RuntimeException{

    public UrlExpiredException() {
    }

    public UrlExpiredException(String message) {
        super(message);
    }

    public UrlExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public UrlExpiredException(Throwable cause) {
        super(cause);
    }

    public UrlExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
