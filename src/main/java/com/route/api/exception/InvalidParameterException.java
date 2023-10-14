package com.route.api.exception;

public class InvalidParameterException extends Exception{
    public InvalidParameterException() {
        super();
    }

    public InvalidParameterException(String message) {
        super(message);
    }
}
