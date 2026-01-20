package edu.icet.ticketmaster.service;

import edu.icet.ticketmaster.annotation.AuditFailure;
import edu.icet.ticketmaster.model.dto.PricingResult;
import edu.icet.ticketmaster.model.entity.Booking;
import edu.icet.ticketmaster.model.entity.Seat;
import edu.icet.ticketmaster.model.entity.User;
import edu.icet.ticketmaster.repository.BookingRepository;
import edu.icet.ticketmaster.repository.SeatRepository;
import edu.icet.ticketmaster.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private PriceCalculatorService priceCalculatorService;

    @AuditFailure
    @Transactional
    public void createBooking(int userId, int seatId, boolean paymentSuccess) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User Not Found.");
        }

        Optional<Seat> seat = seatRepository.findById(seatId);
        if (seat.isEmpty()) {
            throw new RuntimeException("Seat not found");
        }

        // Seat must be HELD by the same user
        if (seat.get().getStatus() != Seat.SeatStatus.HELD ||
                seat.get().getHeldByUserId() != userId) {
            throw new RuntimeException("Seat is not held by this user");
        }

        PricingResult pricing = priceCalculatorService.calculatePrice(user.get(), seat.get().getEvent());

        Booking booking = new Booking();
        booking.setUser(user.get());
        booking.setSeat(seat.get());
        booking.setAmountPaid(pricing.getFinalPrice());

        if (paymentSuccess) {
            booking.setStatus(Booking.BookingStatus.CONFIRMED);
            seat.get().setStatus(Seat.SeatStatus.SOLD);
            seat.get().setHoldExpiry(null);
        } else {
            booking.setStatus(Booking.BookingStatus.CANCELLED);
            seat.get().setStatus(Seat.SeatStatus.AVAILABLE);
            seat.get().setHeldByUserId(0);
            seat.get().setHoldExpiry(null);
        }

        seatRepository.save(seat.get());
        bookingRepository.save(booking);
    }
}

