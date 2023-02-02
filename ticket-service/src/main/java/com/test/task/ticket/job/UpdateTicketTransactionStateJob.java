package com.test.task.ticket.job;

import com.test.task.ticket.access.domain.Ticket;
import com.test.task.ticket.access.domain.Voyage;
import com.test.task.ticket.access.domain.enumes.State;
import com.test.task.ticket.service.TicketService;
import com.test.task.ticket.service.VoyageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class UpdateTicketTransactionStateJob {

    private final TicketService ticketService;
    private final VoyageService voyageService;

    private static final long DELAY_MS = 30000;

    @Scheduled(fixedDelay = DELAY_MS, initialDelay = DELAY_MS)
    public void execute() {
        List<Ticket> tickets = ticketService.findAllByState(State.NEW);
        tickets.forEach(ticket -> {
            Long id = ticket.getTransactionId();
            State state = ticketService.checkTicketTransactionState(id);

            if (State.DONE.equals(state)) {
                ticket.setState(state);
                ticketService.saveTicket(ticket);
            } else if (State.FAILED.equals(state)) {
                ticket.setState(state);
                Voyage voyage = ticket.getVoyage();
                int availableTickets = voyage.getAvailableTickets() + 1;
                voyage.setAvailableTickets(availableTickets);
                voyageService.saveVoyage(voyage);
                ticketService.saveTicket(ticket);
            }
        });
    }
}
