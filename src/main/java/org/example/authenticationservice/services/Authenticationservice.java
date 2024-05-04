package org.example.authenticationservice.services;

//import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.Setter;
import org.example.authenticationservice.dtos.*;
import org.example.authenticationservice.exception.UserAlreadyExistException;
import org.example.authenticationservice.exception.UserDoesnotExists;
import org.example.authenticationservice.models.Session;
import org.example.authenticationservice.models.SessionStatus;
import org.example.authenticationservice.models.User;
import org.example.authenticationservice.repository.SessionRepository;
import org.example.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Optional;

@Service
@Getter
@Setter
public class Authenticationservice
{
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserRepository userRepository;
//
//    @Autowired
//    private BcryptPasswordEncoder passwordEncoder;
//    BCryptPasswordEncoder
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<UserDto> login(String email, String password)  throws  Exception
    {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty())
        {
            throw new UserDoesnotExists("User does not exist Username : " + email);


        }

        User user= userOptional.get();

        String savedPassword=user.getPassword();
        String typedPassword= password;


        boolean isMatched= passwordEncoder.matches(typedPassword, savedPassword);
        if(!isMatched)
        {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        long expiryTime= System.currentTimeMillis()+3600000;
        Date date= new Date(expiryTime);

        String jwtToken= "I'm dummy token";
//                Jwts.builder()
//                .setSubject(" User Login" + user.getEmail())
//                .setIssuedAt(new Date())
//                .setExpiration(date)
//                .claim("roles", user.getRoles())
//                .claim("userid", user.getId())
//                .claim("email", user.getEmail())
//                .compact();

        UserDto userDto= UserDto.from(user);

        HttpHeaders headers= new HttpHeaders();
        headers.add("Authorization_TOKEN", jwtToken);
        ResponseEntity<UserDto> responseEntity= new ResponseEntity<>(userDto, headers, HttpStatus.OK);

        Session session= new Session();
        session.setToken(jwtToken);

        session.setUser(user);
        session.setExpiresAt(date);
        session.setSessionStatus(SessionStatus.ACTIVE);

        sessionRepository.save(session);


      return responseEntity;
    }


    public ResponseEntity<UserDto> signup(String email, String password) throws Exception
    {

        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent())
        {
            throw  new UserAlreadyExistException("User already exists with email : " + email);

        }

        User user= new User();
        user.setEmail(email);

//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);


        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }


    public  ResponseEntity<String> logout(String token, Long userId)
    {

        Optional<Session> sessionOptional= sessionRepository.findByTokenAndUser_id(token, userId);

        if(sessionOptional.isEmpty())
        {
            return new ResponseEntity<>("Session doesn't exists",HttpStatus.UNAUTHORIZED);
        }
        Session session = sessionOptional.get();
        session.setSessionStatus(SessionStatus.LOGGED_OUT);
        sessionRepository.save(session);
        return new ResponseEntity<>("Successfully logged out ",HttpStatus.OK);
    }
    public ResponseEntity<ValidateTokenResponseDto> validate(Long userId, String token)
    {

        Optional<Session> sessionOptional= sessionRepository.findByTokenAndUser_id(token, userId);
        ValidateTokenResponseDto validateTokenResponseDto= new ValidateTokenResponseDto();

        if(sessionOptional.isEmpty()){
           validateTokenResponseDto.setSessionStatus(SessionStatus.INVALID);
            return new ResponseEntity<>(validateTokenResponseDto, HttpStatus.UNAUTHORIZED);
        }

        Session session= sessionOptional.get();
        UserDto userDto= UserDto.from(session.getUser());
//      ValidateTokenResponseDto validateTokenResponseDto= new ValidateTokenResponseDto();
        validateTokenResponseDto.setUserDto(userDto);
        if(!session.getSessionStatus().equals(SessionStatus.ACTIVE))
        {
            validateTokenResponseDto.setSessionStatus(session.getSessionStatus());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if(session.getExpiresAt().compareTo(new Date())<0)
        {
            validateTokenResponseDto.setSessionStatus(SessionStatus.EXPIRED);
            session.setSessionStatus(SessionStatus.EXPIRED);
            sessionRepository.save(session);
            return new ResponseEntity<>(validateTokenResponseDto, HttpStatus.UNAUTHORIZED);
        }
        validateTokenResponseDto.setSessionStatus(SessionStatus.ACTIVE);
        return new ResponseEntity<>(validateTokenResponseDto, HttpStatus.OK);
    }
}
