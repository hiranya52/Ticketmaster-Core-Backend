package edu.icet.ticketmaster.model.entity;

import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class Event {

    @Id
    private Long id;
    private String name;
    private double basePrice;
    private boolean isHighDemand;
    private LocalDateTime eventDate;

}
