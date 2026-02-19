package edu.icet.ticketmaster.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {

    @Id
    private int id;
    private String name;
    private double basePrice;
    private boolean isHighDemand;
    private LocalDateTime eventDate;

}
