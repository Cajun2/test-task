package com.test.task.ticket.access.repository;

import com.test.task.ticket.access.domain.Ticket;
import com.test.task.ticket.access.domain.enumes.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByState(State state);
}
