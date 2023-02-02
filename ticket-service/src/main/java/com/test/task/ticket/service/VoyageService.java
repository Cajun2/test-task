package com.test.task.ticket.service;

import com.test.task.ticket.access.domain.Voyage;

import java.util.List;

public interface VoyageService {

    List<Voyage> findAllVoyages();

    Voyage findVoyageById(Long id);

    Long purchaseTicket(Long voyageId, String firstName, String lastName, String patronymic);

    void saveVoyage(Voyage voyage);
}
