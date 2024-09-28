package com.semicolon.africa.dtos.requests;

import com.semicolon.africa.data.models.enums.Industry;
import com.semicolon.africa.data.models.enums.Role;
import com.semicolon.africa.exception.InvalidNameException;
import lombok.Getter;
import lombok.Setter;

import static java.lang.System.in;
import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Setter
@Getter
public class UserRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Industry industry;
    private Role role;

    public void validate(String name){
        if(name != "" && name != " " ){this.firstName = name;}
        else{throw new InvalidNameException("Please enter a valid name.");}
    }
    public void validateEmail(String email){
        if(email != "" && email != " " && email.contains("@")){this.email = email;}
        else{throw new InvalidNameException("Please enter a valid email.");}
    }
    public void validatePassword(String password){
        if(password != "" && password != " " && password.length() != 8){this.password = password;}
        else{throw new InvalidNameException("Weak password.");}
    }
}
