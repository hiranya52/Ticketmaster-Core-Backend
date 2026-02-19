package edu.icet.ticketmaster.model.dto;

import edu.icet.ticketmaster.model.entity.Event;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SeatDTO {

    private int id;
    private int eventId;
    private String seatNumber;
    private String status;
    private int heldByUserId;
    private LocalDateTime holdExpiry;

}
