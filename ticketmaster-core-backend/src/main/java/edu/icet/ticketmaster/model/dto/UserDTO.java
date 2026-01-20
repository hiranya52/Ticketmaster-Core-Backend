package edu.icet.ticketmaster.model.dto;

import edu.icet.ticketmaster.model.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    private int id;
    private String name;
    private String tier;
    private String email;

    public enum Tier {
        REGULAR,
        VIP,
        PLATINUM
    }

}
