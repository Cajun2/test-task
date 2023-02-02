package com.test.task.payment.validators.annotations.impl;

import com.test.task.payment.validators.annotations.TransactionValueConstrain;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TransactionValueValidator implements ConstraintValidator<TransactionValueConstrain, Double> {

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value < 0.0) {
            changeContextDefaultMessage(context, "Transaction value cannot be less then 0");
            return false;
        }

        String[] split = value.toString().split("\\.");
        if(split[1].length() > 2){
            changeContextDefaultMessage(context, "Transaction value cannot have more then 2 digits after point");
            return false;
        }
        return true;
    }

    private void changeContextDefaultMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation().disableDefaultConstraintViolation();
    }
}
