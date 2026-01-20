package edu.icet.ticketmaster.model.dto;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
