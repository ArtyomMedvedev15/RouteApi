package com.route.api.exception;

public class LimitRequestException extends Exception{
    public LimitRequestException() {
        super();
    }

    public LimitRequestException(String message) {
        super(message);
    }
}
