package edu.icet.ticketmaster.model.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import java.time.LocalDateTime;

public class Seat {

    @Id
    private int id;

    @JoinColumn(name = "event_id")
    private int event;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    private int heldByUserId;

    private LocalDateTime holdExpiry;

    public enum SeatStatus {
        AVAILABLE,
        HELD,
        SOLD
    }

}
