package com.test.task.ticket.controllers;

import com.test.task.ticket.access.domain.Ticket;
import com.test.task.ticket.dto.TicketInfoDto;
import com.test.task.ticket.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final ConversionService conversionService;

    @GetMapping("{id}/check")
    public @ResponseBody TicketInfoDto checkTicketState(@PathVariable(value = "id") Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        return conversionService.convert(ticket, TicketInfoDto.class);
    }
}
