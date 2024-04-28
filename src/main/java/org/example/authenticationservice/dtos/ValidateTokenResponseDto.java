package org.example.authenticationservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.authenticationservice.models.SessionStatus;
@Getter
@Setter
public class ValidateTokenResponseDto {

    private UserDto userDto;
    private SessionStatus sessionStatus;
}
