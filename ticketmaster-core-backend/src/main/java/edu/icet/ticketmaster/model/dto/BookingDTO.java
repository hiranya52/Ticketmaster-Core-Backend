package edu.icet.ticketmaster.model.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingDTO {

    private int id;
    private int userId;
    private int seatId;
    private double amountPaid;
    private BookingStatus status;

    public enum BookingStatus {
        CONFIRMED,
        CANCELLED
    }

}
