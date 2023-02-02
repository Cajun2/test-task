package com.test.task.ticket.dto.converters;

import com.test.task.ticket.access.domain.Voyage;
import com.test.task.ticket.dto.VoyageDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;


@ExtendWith(value = {MockitoExtension.class})
class VoyageDtoConverterTest {

    VoyageDtoConverter voyageDtoConverter = new VoyageDtoConverter();

    @Test
    void shouldConvertVoyageToVoyageDto(){
        //GIVEN
        Long id = 10L;
        String departurePlace = "department";
        String arrivalPlace = "arrival";
        double price = 100.00;
        int ticketsAvailable = 10;
        Date arrivalTime = new Date();
        Date departureTime = new Date();

        VoyageDto expectedDto = VoyageDto.builder().availableTickets(ticketsAvailable).id(id).price(price)
                .arrivalTime(arrivalTime).arrivalPlace(arrivalPlace).departureTime(departureTime).departurePlace(departurePlace)
                .build();

        Voyage voyage = Voyage.builder().departurePlace(departurePlace).arrivalTime(arrivalTime).price(price).id(id)
                .availableTickets(ticketsAvailable).arrivalPlace(arrivalPlace).departureTime(departureTime)
                .build();

        //WHEN
        VoyageDto actualDto = voyageDtoConverter.convert(voyage);

        //THEN
        Assertions.assertEquals(expectedDto, actualDto);
    }
}