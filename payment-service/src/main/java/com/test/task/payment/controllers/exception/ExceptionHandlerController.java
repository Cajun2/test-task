package com.test.task.payment.controllers.exception;

import com.test.task.payment.exceptions.GenericException;
import com.test.task.payment.data.access.domain.enums.GenericStatus;
import com.test.task.payment.exceptions.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidHandler(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        errors.put("message", "Validation error. Please fill all the fields correctly and try again.");
        errors.put("status", GenericStatus.FAILED.name());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorDetails> exceptionHandler(GenericException ex) {
        return new ResponseEntity<>(new ErrorDetails(ex.getMessage(), GenericStatus.FAILED.name()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDetails> methodArgumentTypeMismatchExceptionHandler(
            MethodArgumentTypeMismatchException ex) {
        String message = "Incorrect input data";

        return new ResponseEntity<>(
                new ErrorDetails(message, GenericStatus.FAILED.name()), HttpStatus.BAD_REQUEST);
    }
}
