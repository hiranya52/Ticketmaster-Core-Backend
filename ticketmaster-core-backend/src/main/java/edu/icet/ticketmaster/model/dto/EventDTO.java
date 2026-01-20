package edu.icet.ticketmaster.model.dto;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventDTO {

    private int id;
    private String name;
    private double basePrice;
    private boolean isHighDemand;
    private LocalDateTime eventDate;

}
