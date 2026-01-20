package edu.icet.ticketmaster.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
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
