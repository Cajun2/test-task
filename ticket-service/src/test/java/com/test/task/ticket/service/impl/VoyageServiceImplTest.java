package com.test.task.ticket.service.impl;

import com.test.task.ticket.access.domain.Ticket;
import com.test.task.ticket.access.domain.Voyage;
import com.test.task.ticket.access.repository.VoyageRepository;
import com.test.task.ticket.exceptions.GenericException;
import com.test.task.ticket.service.TicketService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(value = {MockitoExtension.class})
public class VoyageServiceImplTest {

    @Mock
    private VoyageRepository voyageRepository;

    @Mock
    private TicketService ticketService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private VoyageServiceImpl voyageService;

    @Test
    void shouldPurchaseTicket() {
        //GIVEN
        Voyage voyage = Voyage.builder().availableTickets(10).build();
        when(voyageRepository.findById(any())).thenReturn(Optional.of(voyage));
        when(ticketService.saveTicket(any())).thenReturn(new Ticket());

        //WHEN
        voyageService.purchaseTicket(10L, "name", "lastName", "patronymic");

        //THEN
        Assertions.assertEquals(voyage.getAvailableTickets(), 9);
    }

    @Test
    void shouldThrowExceptionWhenVoyageDoesntExist() {
        //GIVEN
        //WHEN
        //THEN
        Assertions.assertThrows(GenericException.class,
                () -> voyageService.purchaseTicket(10L, "name", "lastName", "patronymic"));
    }

    @Test
    void shouldThrowExceptionWhenNoAvailableTickets() {
        //GIVEN
        Voyage voyage = Voyage.builder().availableTickets(0).build();
        when(voyageRepository.findById(any())).thenReturn(Optional.of(voyage));
        //WHEN
        //THEN
        Assertions.assertThrows(GenericException.class,
                () -> voyageService.purchaseTicket(10L, "name", "lastName", "patronymic"));
    }
}
