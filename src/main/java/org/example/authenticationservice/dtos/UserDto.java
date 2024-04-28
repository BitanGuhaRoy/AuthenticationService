package org.example.authenticationservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.authenticationservice.models.Role;
import org.example.authenticationservice.models.User;

import java.util.HashSet;
@Getter
@Setter
public class UserDto {

    private String email;
//    private String message;
    private HashSet<Role> roles= new HashSet<>();
//    public UserDto(){
//        this.roles= new HashSet<>();
//    }

    public static UserDto from(User user)
    {
        UserDto userDto= new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoles(new HashSet<>(user.getRoles()));
        return userDto;
    }
}
