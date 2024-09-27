package com.semicolon.africa.data.models;

import com.semicolon.africa.data.models.enums.Industry;
import com.semicolon.africa.data.models.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Industry industry;
    private Role role;


}
