package edu.icet.ticketmaster.repository;

import edu.icet.ticketmaster.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
}
