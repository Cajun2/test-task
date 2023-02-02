package com.test.task.payment.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class ErrorDetails {
    private String message;
    private String status;

    private Class<? extends Exception> errorClass;
    private String errorStackTrace;

    public ErrorDetails(Exception ex, String message, String status) {
        this.errorClass = ex.getClass();

        this.errorStackTrace = Arrays.stream(ex.getStackTrace())
                .limit(50)
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
        this.message = message + ex.getMessage();
        this.status = status;
    }

    public ErrorDetails(String message, String status) {
        this.message = message;
        this.status = status;
    }

}
