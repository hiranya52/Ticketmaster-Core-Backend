package edu.icet.ticketmaster.service;

import edu.icet.ticketmaster.model.dto.EventDTO;
import edu.icet.ticketmaster.model.entity.Event;
import edu.icet.ticketmaster.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public void save(EventDTO eventDTO) {

        Event event = new Event(
                eventDTO.getId(),
                eventDTO.getName(),
                eventDTO.getBasePrice(),
                eventDTO.isHighDemand(),
                eventDTO.getEventDate()
        );

        eventRepository.save(event);

    }


}
