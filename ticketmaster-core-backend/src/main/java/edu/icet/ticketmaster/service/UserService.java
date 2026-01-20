package edu.icet.ticketmaster.service;

import edu.icet.ticketmaster.model.dto.UserDTO;
import edu.icet.ticketmaster.model.entity.User;
import edu.icet.ticketmaster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void save(UserDTO userDTO) {

        User user = new User(
                userDTO.getId(),
                userDTO.getName(),
                User.Tier.valueOf(userDTO.getTier()),
                userDTO.getEmail()
        );

        userRepository.save(user);

    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }


}
