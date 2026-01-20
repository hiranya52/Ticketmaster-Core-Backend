package edu.icet.ticketmaster.repository;

import edu.icet.ticketmaster.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
