package org.example.authenticationservice.services;

import org.example.authenticationservice.dtos.UserDto;
import org.example.authenticationservice.models.Role;
import org.example.authenticationservice.models.User;
import org.example.authenticationservice.repository.RoleRepository;
import org.example.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Userservice {

     @Autowired
    private UserRepository  userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<UserDto> getUserDetails(Long id) {


         Optional<User> userOptional = userRepository.findById(id);
         if(userOptional.isEmpty())
         {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }

            User user = userOptional.get();
            UserDto userDto = UserDto.from(user);

            return new ResponseEntity<>(userDto, HttpStatus.OK);

     }

    public ResponseEntity<UserDto>setUserRoles(Long id, List<Long> roleids)
    {
         Optional<User> userOptional = userRepository.findById(id);
            if(userOptional.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            User user = userOptional.get();

            List<Role> roles = roleRepository.findAllById(roleids);
            user.setRoles(new ArrayList<>(roles));
            userRepository.save(user);
            UserDto userDto = UserDto.from(user);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
