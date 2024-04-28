package org.example.authenticationservice.controllers;


import org.example.authenticationservice.dtos.SetUserRolesRequestDto;
import org.example.authenticationservice.dtos.UserDto;
import org.example.authenticationservice.services.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class Usercontroller {

    @Autowired
    private Userservice userservice;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetailsd(@PathVariable Long id) {
        return userservice.getUserDetails(id);
    }


    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDto> setUserroles(@PathVariable Long id, @RequestBody SetUserRolesRequestDto setUserRolesRequestDto)
    {

        return userservice.setUserRoles(id,setUserRolesRequestDto.getRoleIds());
    }


}
