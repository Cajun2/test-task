package com.test.task.ticket.service;

import com.test.task.ticket.access.domain.Ticket;
import com.test.task.ticket.access.domain.enumes.State;

import java.util.List;

public interface TicketService {

    Ticket saveTicket(Ticket ticket);

    Ticket getTicketById(Long id);

    List<Ticket> findAllByState(State state);

    State checkTicketTransactionState(Long transactionId);
}
