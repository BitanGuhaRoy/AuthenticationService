package org.example.authenticationservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SetUserRolesRequestDto {

    List<Long> roleIds;
}
