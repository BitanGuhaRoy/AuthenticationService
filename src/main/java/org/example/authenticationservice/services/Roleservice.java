package org.example.authenticationservice.services;

import org.example.authenticationservice.dtos.CreateRoleRequestDto;
import org.example.authenticationservice.dtos.CreateRoleResponseDto;
import org.example.authenticationservice.models.Role;
import org.example.authenticationservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Roleservice {

    @Autowired
    private RoleRepository roleRepository;
    public ResponseEntity<CreateRoleResponseDto> createRole(String name) {

        Role role = new Role();
        role.setName(name);

        roleRepository.save(role);

        CreateRoleResponseDto createRoleResponseDto = new CreateRoleResponseDto();
        createRoleResponseDto.setRole(role);
        createRoleResponseDto.setRoleId(role.getId());
        return new ResponseEntity<>(createRoleResponseDto, HttpStatus.OK);


    }
}
