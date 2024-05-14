package org.example.authenticationservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;
import org.example.authenticationservice.models.Role;
import org.springframework.security.core.GrantedAuthority;
@JsonDeserialize
@NoArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;
    CustomGrantedAuthority(Role role)
    {
        this.authority = role.getName();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
