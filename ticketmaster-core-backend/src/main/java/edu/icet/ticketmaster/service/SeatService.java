package edu.icet.ticketmaster.service;

import edu.icet.ticketmaster.exception.SeatLockedException;
import edu.icet.ticketmaster.model.dto.SeatDTO;
import edu.icet.ticketmaster.model.entity.Seat;
import edu.icet.ticketmaster.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

    private void lockSeat(Seat seat, int userId) {
        seat.setStatus(Seat.SeatStatus.HELD);
        seat.setHeldByUserId(userId);
        seat.setHoldExpiry(LocalDateTime.now().plusMinutes(10));
        seatRepository.save(seat);
    }

    private void releaseExpiredHold(Seat seat) {
        if (seat.getStatus() == Seat.SeatStatus.HELD &&
                seat.getHoldExpiry() != null &&
                seat.getHoldExpiry().isBefore(LocalDateTime.now())) {

            seat.setStatus(Seat.SeatStatus.AVAILABLE);
            seat.setHeldByUserId(0);
            seat.setHoldExpiry(null);

            seatRepository.save(seat);
        }
    }


    public SeatDTO holdSeat(int seatId, int userId) {

        Seat seat = seatRepository.findById(seatId).orElseThrow(
                () -> new RuntimeException("Seat not found")
        );

        // Release if expired
        releaseExpiredHold(seat);

        // Now check status again
        if (seat.getStatus() == Seat.SeatStatus.AVAILABLE) {
            lockSeat(seat, (int) userId);

            return new SeatDTO(
                    seat.getId(),
                    seat.getEvent().getId(),
                    seat.getSeatNumber(),
                    seat.getStatus().name(),
                    seat.getHeldByUserId(),
                    seat.getHoldExpiry()
            );
        }

        if (seat.getStatus() == Seat.SeatStatus.HELD) {
            long remainingSeconds = Duration.between(LocalDateTime.now(), seat.getHoldExpiry()).getSeconds();
            throw new SeatLockedException("Seat is locked. Remaining seconds: " + remainingSeconds);
        }

        throw new RuntimeException("Seat already sold");
    }


}
