package com.test.task.ticket.exceptions;

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