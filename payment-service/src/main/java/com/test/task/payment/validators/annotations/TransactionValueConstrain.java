package com.test.task.payment.validators.annotations;

import com.test.task.payment.validators.annotations.impl.TransactionValueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;


@Constraint(validatedBy = TransactionValueValidator.class)
@Target({FIELD, PARAMETER, ANNOTATION_TYPE, TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionValueConstrain {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
