package org.example.authenticationservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.authenticationservice.models.Role;

@Getter
@Setter
public class CreateRoleResponseDto {

    private Role role;
    private Long roleId;
}
