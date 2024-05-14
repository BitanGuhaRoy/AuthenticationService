package org.example.authenticationservice.security.services;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.authenticationservice.models.User;
import org.example.authenticationservice.repository.UserRepository;
import org.example.authenticationservice.security.models.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@JsonDeserialize
public class CustomUserDetailsService implements UserDetailsService {
     @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByEmail(username);
        if(userOptional.isEmpty())
        {
            throw new UsernameNotFoundException("User does not exist Username : " + username);
        }

        return new CustomUserDetails(userOptional.get());
//        return  null;
    }
}
