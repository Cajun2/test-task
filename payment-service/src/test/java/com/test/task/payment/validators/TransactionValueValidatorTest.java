package com.test.task.payment.validators;

import com.test.task.payment.dto.CreateTransactionDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(value = {MockitoExtension.class})
public class TransactionValueValidatorTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @Test
    void shouldReturnConstraintWhenValueHasMoreThen2Digits(){
        //GIVEN
        double value = 100.111;
        CreateTransactionDto createTransactionDto = CreateTransactionDto.builder()
                .firstName("name").lastName("lastname").patronymic("some")
                .value(value).build();
        //WHEN
        Set<ConstraintViolation<CreateTransactionDto>> validations = validator.validate(createTransactionDto);

        //THEN
        Assertions.assertTrue(validations.stream().anyMatch(o -> o.getMessage().equals("Transaction value cannot have more then 2 digits after point")));
    }

    @Test
    void shouldReturnConstraintWhenValueIsNegative(){
        //GIVEN
        double value = -100.11;
        CreateTransactionDto createTransactionDto = CreateTransactionDto.builder()
                .firstName("name").lastName("lastname").patronymic("some")
                .value(value).build();
        //WHEN
        Set<ConstraintViolation<CreateTransactionDto>> validations = validator.validate(createTransactionDto);

        //THEN
        Assertions.assertTrue(validations.stream().anyMatch(o -> o.getMessage().equals("Transaction value cannot be less then 0")));
    }
}
