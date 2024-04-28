package org.example.authenticationservice.controllers;

import org.example.authenticationservice.dtos.*;
import org.example.authenticationservice.services.Authenticationservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class Authcontroller {

   @Autowired
   private Authenticationservice authenticationservice;

    @PostMapping("/login")
   public ResponseEntity<UserDto> login(@RequestBody  LoginRequestDto loginRequestDto) throws  Exception
   {
//               System.out.println(loginRequestDto.getEmail());
//        System.out.println(loginRequestDto.getPassword());

       return authenticationservice.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
   }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto signupRequestDto) throws  Exception
    {
//        System.out.println(signupRequestDto.getEmail());
//        System.out.println(signupRequestDto.getPassword());
         return authenticationservice.signup(signupRequestDto.getEmail(), signupRequestDto.getPassword());
    }

    @PostMapping("/logout")
    public  ResponseEntity<String> logout(@RequestBody LogoutRequestDto logoutRequestDto)
    {
        return authenticationservice.logout(logoutRequestDto.getToken(), logoutRequestDto.getUserId());
    }


    @PostMapping("/validate")
    public ResponseEntity<ValidateTokenResponseDto> validate(@RequestBody ValidateTokenRequestDto validateTokenRequestDto)
    {
        return authenticationservice.validate(validateTokenRequestDto.getUserId(), validateTokenRequestDto.getToken());
    }

}
