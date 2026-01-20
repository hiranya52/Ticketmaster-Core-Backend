package edu.icet.ticketmaster.controller;

import edu.icet.ticketmaster.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/book")
    public void createBooking(@RequestParam int userId, @RequestParam int seatId, @RequestParam boolean paymentSuccess) {
        bookingService.createBooking(userId, seatId, paymentSuccess);
    }

}
