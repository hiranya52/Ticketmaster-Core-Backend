package edu.icet.ticketmaster.service;

import edu.icet.ticketmaster.model.dto.PricingResult;
import edu.icet.ticketmaster.model.entity.Event;
import edu.icet.ticketmaster.model.entity.User;
import org.springframework.stereotype.Service;


@Service
public class PriceCalculatorService {

    public PricingResult calculatePrice(User user, Event event) {
        if (user.getTier() == User.Tier.REGULAR) {
            return regularPrice(event);
        } else if (user.getTier() == User.Tier.VIP) {
            return vipPrice(event);
        } else {
            return platinumPrice(event);
        }
    }

    /* ---------- STRATEGY METHODS ---------- */

    private PricingResult regularPrice(Event event) {
        return new PricingResult(event.getBasePrice(), false);
    }

    private PricingResult vipPrice(Event event) {

        if (event.isHighDemand()) {
            return new PricingResult(event.getBasePrice(), false);
        }

        return new PricingResult(event.getBasePrice() * 0.9, false);
    }

    private PricingResult platinumPrice(Event event) {
        return new PricingResult(event.getBasePrice(), true);
    }

}
