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

    public SeatDTO holdSeat(int seatId, int userId) {

        Seat seat = seatRepository.findById(seatId).get();

        if (seat == null) {
            throw new RuntimeException("Seat not found");
        }

        // If seat is AVAILABLE -> hold it
        if (seat.getStatus() == Seat.SeatStatus.AVAILABLE) {
            seat.setStatus(Seat.SeatStatus.HELD);
            seat.setHeldByUserId(userId);
            seat.setHoldExpiry(LocalDateTime.now().plusMinutes(10));

            seatRepository.save(seat);

            return new SeatDTO(
                    seat.getId(),
                    seat.getEvent().getId(),
                    seat.getSeatNumber(),
                    seat.getStatus().name(),
                    seat.getHeldByUserId(),
                    seat.getHoldExpiry()
            );
        }
        //if sea holds expire
        if (seat.getStatus() == Seat.SeatStatus.HELD) {

            if (seat.getHoldExpiry().isBefore(LocalDateTime.now())) {
                seat.setHeldByUserId(userId);
                seat.setHoldExpiry(LocalDateTime.now().plusMinutes(10));

                seatRepository.save(seat);

                return new SeatDTO(
                        seat.getId(),
                        seat.getEvent().getId(),
                        seat.getSeatNumber(),
                        seat.getStatus().name(),
                        seat.getHeldByUserId(),
                        seat.getHoldExpiry()
                );
            }

            // If hold still active -> throw custom exception
            long remainingSeconds = Duration.between(LocalDateTime.now(), seat.getHoldExpiry()).getSeconds();
            throw new SeatLockedException("Seat is locked. Remaining seconds: " + remainingSeconds);
        }

        // If seat is already SOLD
        throw new RuntimeException("Seat already sold");
    }

}
