package edu.icet.ticketmaster.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PricingResult {

    private double finalPrice;
    private boolean priorityAccess;

}
