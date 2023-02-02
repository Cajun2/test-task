package com.test.task.payment.service;

import com.test.task.payment.data.access.domain.enums.State;
import com.test.task.payment.data.access.domain.Transaction;

public interface TransactionService {

    Transaction createTransaction(String firstName, String lastName, String patronymic, Double value);

    State getRandomTransactionState(Long id);
}
