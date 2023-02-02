package com.test.task.payment.controllers;

import com.test.task.payment.data.access.domain.enums.State;
import com.test.task.payment.data.access.domain.Transaction;
import com.test.task.payment.dto.CreateTransactionDto;
import com.test.task.payment.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private TransactionService transactionService;

    @GetMapping(value = "{id}/check")
    public State checkPaymentState(@PathVariable("id") Long id) {
        return transactionService.getRandomTransactionState(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Long createPayment(@RequestBody @Valid CreateTransactionDto dto) {
        Transaction transaction = transactionService.createTransaction(dto.getFirstName(), dto.getLastName(), dto.getPatronymic(), dto.getValue());
        return transaction.getId();
    }
}
