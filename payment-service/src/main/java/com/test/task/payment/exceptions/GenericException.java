package com.test.task.payment.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GenericException extends RuntimeException {

    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericException(String message) {
        super(message);
    }
}