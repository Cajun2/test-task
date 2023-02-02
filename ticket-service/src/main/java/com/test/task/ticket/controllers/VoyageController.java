package com.test.task.ticket.controllers;

import com.test.task.ticket.dto.PurchaseTicketDto;
import com.test.task.ticket.dto.VoyageDto;
import com.test.task.ticket.service.VoyageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/voyage")
public class VoyageController {

    private final VoyageService voyageService;
    private final ConversionService conversionService;

    @GetMapping
    public @ResponseBody List<VoyageDto> showAllVoyages() {
        return voyageService.findAllVoyages().stream()
                .map(voyage -> conversionService.convert(voyage, VoyageDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping("{id}/purchase")
    public Long purchaseTicket(@PathVariable(value = "id") Long id, @RequestBody @Valid PurchaseTicketDto dto) {
        return voyageService.purchaseTicket(id, dto.getFirstName(), dto.getFirstName(), dto.getPatronymic());
    }

}
