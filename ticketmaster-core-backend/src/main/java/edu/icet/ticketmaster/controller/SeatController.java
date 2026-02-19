package edu.icet.ticketmaster.controller;

import edu.icet.ticketmaster.model.dto.SeatDTO;
import edu.icet.ticketmaster.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    SeatService seatService;

    @GetMapping
    public String loadseatController(){
        return "Load Seat Controller..";
    }

    @PostMapping("/{id}/hold")
    public void holdSeat(@PathVariable int id, @RequestParam("userId") int userId){
            SeatDTO seatDTO = seatService.holdSeat(id, userId);
    }


}
