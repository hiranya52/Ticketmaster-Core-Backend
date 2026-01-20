package edu.icet.ticketmaster.model.dto;

import lombok.*;
import org.springframework.stereotype.Service;

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
