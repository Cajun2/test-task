package com.test.task.ticket.service.impl;

import com.test.task.ticket.access.domain.Ticket;
import com.test.task.ticket.access.domain.Voyage;
import com.test.task.ticket.access.domain.enumes.State;
import com.test.task.ticket.access.repository.VoyageRepository;
import com.test.task.ticket.dto.TransactionDto;
import com.test.task.ticket.exceptions.GenericException;
import com.test.task.ticket.service.TicketService;
import com.test.task.ticket.service.VoyageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class VoyageServiceImpl implements VoyageService {

    private static final String PAYMENT_APPLICATION = "http://PAYMENT-APPLICATION/test-task/payment";
    private static final String CREATE_TRANSACTION = "transaction";

    private final VoyageRepository voyageRepository;
    private final TicketService ticketService;
    private final RestTemplate restTemplate;

    @Override
    public List<Voyage> findAllVoyages() {
        return voyageRepository.findAll();
    }

    @Override
    public Voyage findVoyageById(Long id) {
        return voyageRepository.findById(id).orElseThrow(
                () -> new GenericException(String.format("Voyage with id = %s does not exist!", id)));
    }

    @Override
    @Transactional
    public Long purchaseTicket(Long voyageId, String firstName, String lastName, String patronymic) {
        Voyage voyage = findVoyageById(voyageId);
        if (voyage.getAvailableTickets() <= 0) {
            throw new GenericException("There is no ticket avaliable for this voyage");
        }

        TransactionDto transactionDto = new TransactionDto(firstName, lastName, patronymic, voyage.getPrice());

        Long transactionId = restTemplate.postForObject(PAYMENT_APPLICATION + "/" + CREATE_TRANSACTION,
                transactionDto, Long.class);

        int availableTickets = voyage.getAvailableTickets() - 1;
        voyage.setAvailableTickets(availableTickets);
        voyageRepository.save(voyage);

        Ticket ticket = Ticket.builder().voyage(voyage).firstName(firstName).lastName(lastName)
                .patronymic(patronymic).state(State.NEW).transactionId(transactionId).build();

        return ticketService.saveTicket(ticket).getId();
    }

    @Override
    public void saveVoyage(Voyage voyage) {
        voyageRepository.save(voyage);
    }

}
