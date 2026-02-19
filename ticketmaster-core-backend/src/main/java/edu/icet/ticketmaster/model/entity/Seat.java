package edu.icet.ticketmaster.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Seat {


    @Id
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event event;

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
