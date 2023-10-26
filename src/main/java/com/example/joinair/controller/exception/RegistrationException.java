package com.example.joinair.controller.exception;

public class RegistrationException extends Exception {
    public RegistrationException() {
        super();
    }

    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
