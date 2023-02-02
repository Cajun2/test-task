package com.test.task.ticket.dto.converters;

import com.test.task.ticket.access.domain.Ticket;
import com.test.task.ticket.access.domain.Voyage;
import com.test.task.ticket.dto.TicketInfoDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TicketInfoDtoConverter implements Converter<Ticket, TicketInfoDto> {

    @Override
    public TicketInfoDto convert(Ticket ticket) {
        Voyage voyage = ticket.getVoyage();
        return  TicketInfoDto.builder()
                .lastName(ticket.getLastName()).firstName(ticket.getFirstName())
                .patronymic(ticket.getPatronymic()).state(ticket.getState())
                .arrivalPlace(voyage.getArrivalPlace()).departurePlace(voyage.getDeparturePlace())
                .arrivalTime(voyage.getArrivalTime()).departureTime(voyage.getDepartureTime()).price(voyage.getPrice())
                .build();
    }
}
