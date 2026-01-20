package edu.icet.ticketmaster.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {

    @Id
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tier tier;

    private String email;

    public enum Tier {
        REGULAR,
        VIP,
        PLATINUM
    }

}