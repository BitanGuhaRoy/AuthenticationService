package org.example.authenticationservice.controllers;


import org.example.authenticationservice.dtos.CreateRoleRequestDto;
import org.example.authenticationservice.dtos.CreateRoleResponseDto;
import org.example.authenticationservice.models.Role;
import org.example.authenticationservice.services.Roleservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class Rolecontroller {

    @Autowired
    private Roleservice roleservice;

    @PostMapping("/add")
    public ResponseEntity<CreateRoleResponseDto>createRole(@RequestBody CreateRoleRequestDto createRoleRequestDto) {

     String name = createRoleRequestDto.getName();
     return   roleservice.createRole(name);

    }

}
