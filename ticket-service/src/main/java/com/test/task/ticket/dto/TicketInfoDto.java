package com.test.task.ticket.dto;

import com.test.task.ticket.access.domain.enumes.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketInfoDto {

    private double price;

    private String departurePlace;

    private String arrivalPlace;

    private Date departureTime;

    private Date arrivalTime;

    private String firstName;

    private String lastName;

    private String patronymic;

    private State state;
}
