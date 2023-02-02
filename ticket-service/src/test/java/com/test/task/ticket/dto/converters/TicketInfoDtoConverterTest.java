package com.test.task.ticket.dto.converters;

import com.test.task.ticket.access.domain.Ticket;
import com.test.task.ticket.access.domain.Voyage;
import com.test.task.ticket.access.domain.enumes.State;
import com.test.task.ticket.dto.TicketInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

@ExtendWith(value = {MockitoExtension.class})
class TicketInfoDtoConverterTest {

    TicketInfoDtoConverter ticketInfoDtoConverter = new TicketInfoDtoConverter();

    @Test
    void shouldConvertTicketToTicketInfoDto() {
        //GIVEN
        String patronymic = "patronymic";
        String firstName = "name";
        String lastName = "lastName";
        String departurePlace = "department";
        String arrivalPlace = "arrival";
        State state = State.NEW;
        double price = 100.00;
        int ticketsAvailable = 10;
        Date arrivalTime = new Date();
        Date departureTime = new Date();

        Voyage voyage = Voyage.builder().departurePlace(departurePlace).arrivalTime(arrivalTime).price(price)
                .availableTickets(ticketsAvailable).arrivalPlace(arrivalPlace).departureTime(departureTime)
                .build();

        Ticket ticket = Ticket.builder().voyage(voyage).state(state)
                .patronymic(patronymic).lastName(lastName).firstName(firstName)
                .build();

        TicketInfoDto expectedTicketInfoDto = TicketInfoDto.builder().price(price).departureTime(departureTime)
                .arrivalTime(arrivalTime).arrivalPlace(arrivalPlace).departurePlace(departurePlace).state(state)
                .patronymic(patronymic).lastName(lastName).firstName(firstName)
                .build();

        //WHEN
        TicketInfoDto actualTicketInfoDto = ticketInfoDtoConverter.convert(ticket);

        //THEN
        Assertions.assertEquals(expectedTicketInfoDto, actualTicketInfoDto);
    }
}