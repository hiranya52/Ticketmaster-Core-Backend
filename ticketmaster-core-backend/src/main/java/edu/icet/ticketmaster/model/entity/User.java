package edu.icet.ticketmaster.model.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private int id;
    private String name;
    private Tier tier;
    private String email;

    public enum Tier {
        REGULAR,
        VIP,
        PLATINUM
    }

}