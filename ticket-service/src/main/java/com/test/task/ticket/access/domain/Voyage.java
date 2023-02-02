package com.test.task.ticket.access.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voyage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private double price;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "available-tickets")
    private int availableTickets;

    @Column(name = "departure")
    private String departurePlace;

    @Column(name = "arrival")
    private String arrivalPlace;

    @Column(name = "departure_time")
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date departureTime;

    @Column(name = "arrival_time")
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date arrivalTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voyage",fetch = FetchType.EAGER)
    private Set<Ticket> tickets;
}
