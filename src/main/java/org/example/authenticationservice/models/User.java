package org.example.authenticationservice.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User  extends BaseModel{

    private String email;
    private String password;
    @ManyToMany
    private List<Role> roles;
    public User(){
        this.roles= new ArrayList<>();
    }
}
