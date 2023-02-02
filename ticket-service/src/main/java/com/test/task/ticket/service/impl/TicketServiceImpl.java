package com.test.task.ticket.service.impl;

import com.test.task.ticket.access.domain.Ticket;

import com.test.task.ticket.access.domain.enumes.State;
import com.test.task.ticket.access.repository.TicketRepository;
import com.test.task.ticket.exceptions.GenericException;
import com.test.task.ticket.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private static final String PAYMENT_APPLICATION = "http://PAYMENT-APPLICATION/test-task/payment";
    private static final String CHECK = "check";
    private static final String TRANSACTION = "transaction";

    private final TicketRepository ticketRepository;
    private final RestTemplate restTemplate;

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(
                () -> new GenericException(String.format("Ticket with id = %s does not exist!", id)));
    }

    @Override
    public List<Ticket> findAllByState(State state) {
        return ticketRepository.findAllByState(state);
    }

    @Override
    public State checkTicketTransactionState(Long transactionId) {
        return  restTemplate.getForObject(PAYMENT_APPLICATION + "/" + TRANSACTION + "/" + transactionId + "/" + CHECK,
                State.class);
    }

}
