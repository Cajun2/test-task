package com.test.task.ticket.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoyageDto {

    private Long id;

    private double price;

    private int availableTickets;

    private String departurePlace;

    private String arrivalPlace;

    private Date departureTime;

    private Date arrivalTime;


}
