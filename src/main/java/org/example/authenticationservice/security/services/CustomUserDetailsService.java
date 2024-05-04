package org.example.authenticationservice.security.services;

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
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User > user = userRepository.findByEmail(username);
        if(user.isPresent())
        {
            return new CustomUserDetails(user.get());
        }
        else
        {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

    }
}