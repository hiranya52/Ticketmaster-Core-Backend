package edu.icet.ticketmaster.repository;

import edu.icet.ticketmaster.model.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
