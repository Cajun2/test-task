package com.test.task.payment.service.impl;

import com.test.task.payment.data.access.domain.Transaction;
import com.test.task.payment.data.access.domain.enums.State;
import com.test.task.payment.data.access.repository.TransactionRepository;
import com.test.task.payment.exceptions.GenericException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(value = {MockitoExtension.class})
class TransactionServiceImplTest {

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionServiceImpl transactionService;

    @Test
    void shouldCreateTransaction() {
        //GIVEN
        String firstName = "someName";
        String lastName = "lastName";
        String patronymic = "patronymic";
        double value = 100.00;
        Transaction exceptedTransaction = Transaction.builder().firstName(firstName)
                .lastName(lastName).patronymic(patronymic).value(value).state(State.NEW).build();
        when(transactionRepository.save(any())).thenReturn(exceptedTransaction);

        //WHEN
        Transaction actualTransaction = transactionService.createTransaction(firstName, lastName, patronymic, value);

        //THEN
        Assertions.assertEquals(exceptedTransaction, actualTransaction);
    }

    @Test
    void shouldReturnRandomState() {
        //GIVEN
        when(transactionRepository.findById(any())).thenReturn(Optional.of(new Transaction()));
        //WHEN
        //THEN
        Assertions.assertNotNull(transactionService.getRandomTransactionState(any()));
    }

    @Test
    void shouldThrowGenericExceptionWhenTransactionDoesntExist() {
        //GIVEN
        //WHEN
        //THEN
        Assertions.assertThrows(GenericException.class, () -> transactionService.getRandomTransactionState(any()));
    }
}