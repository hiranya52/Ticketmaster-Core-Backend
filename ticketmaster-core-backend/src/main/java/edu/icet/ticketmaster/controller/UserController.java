package edu.icet.ticketmaster.controller;

import edu.icet.ticketmaster.model.dto.UserDTO;
import edu.icet.ticketmaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String loadUserController(){
        return "Load User Controller..";
    }

    @PostMapping("/save")
    public void saveUser(@RequestBody UserDTO userDTO){
        userService.save(userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id){
        userService.delete(id);
    }



}
