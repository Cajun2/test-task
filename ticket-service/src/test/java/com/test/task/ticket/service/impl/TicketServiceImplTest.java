package com.test.task.ticket.service.impl;

import com.test.task.ticket.access.repository.TicketRepository;
import com.test.task.ticket.exceptions.GenericException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(value = {MockitoExtension.class})
class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Test
    void shouldThrowExceptionWhenTicketDoesntExist() {
        //GIVEN
        //WHEN
        //THEN
        Assertions.assertThrows(GenericException.class,
                () -> ticketService.getTicketById(10L));
    }
}