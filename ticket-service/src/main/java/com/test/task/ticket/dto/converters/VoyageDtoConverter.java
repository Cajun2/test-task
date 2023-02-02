package com.test.task.ticket.dto.converters;

import com.test.task.ticket.access.domain.Voyage;
import com.test.task.ticket.dto.VoyageDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VoyageDtoConverter implements Converter<Voyage, VoyageDto> {

    @Override
    public VoyageDto convert(Voyage voyage) {
        return VoyageDto.builder().id(voyage.getId()).price(voyage.getPrice())
                .departurePlace(voyage.getDeparturePlace()).arrivalPlace(voyage.getArrivalPlace())
                .departureTime(voyage.getDepartureTime()).arrivalTime(voyage.getArrivalTime())
                .availableTickets(voyage.getAvailableTickets())
                .build();
    }
}
