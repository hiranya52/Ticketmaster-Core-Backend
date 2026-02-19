package edu.icet.ticketmaster.controller;

import edu.icet.ticketmaster.model.dto.EventDTO;
import edu.icet.ticketmaster.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping
    public String loadEventController(){
        return "Load Event Controller..";
    }

    @PostMapping("/save")
    public void saveEvent(@RequestBody EventDTO eventDTO){
        eventService.save(eventDTO);
    }

}
