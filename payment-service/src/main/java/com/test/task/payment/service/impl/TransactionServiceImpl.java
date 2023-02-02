package com.test.task.payment.service.impl;

import com.test.task.payment.exceptions.GenericException;
import com.test.task.payment.data.access.domain.enums.State;
import com.test.task.payment.data.access.domain.Transaction;
import com.test.task.payment.data.access.repository.TransactionRepository;
import com.test.task.payment.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private static final int SIZE = State.values().length;
    private static final Random random = new Random();
    private static final State[] states = State.values();

    @Override
    public Transaction createTransaction(String firstName, String lastName, String patronymic, Double value) {
        Transaction transaction = Transaction.builder().firstName(firstName)
                .lastName(lastName).patronymic(patronymic).value(value).state(State.NEW).build();
        return transactionRepository.save(transaction);
    }

    @Override
    public State getRandomTransactionState(Long id) {
        transactionRepository.findById(id).orElseThrow(
                ()->new GenericException(String.format("Transaction with id = %s does not exist!", id)));
        return states[random.nextInt(SIZE)];
    }
}
