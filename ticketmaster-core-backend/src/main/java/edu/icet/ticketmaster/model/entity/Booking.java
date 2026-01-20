package edu.icet.ticketmaster.model.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Booking {

    @Id
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Seat seat;

    private double amountPaid;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    public enum BookingStatus {
        CONFIRMED,
        CANCELLED
    }

}
