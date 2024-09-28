package com.semicolon.africa.utilities;

import com.semicolon.africa.data.models.User;
import com.semicolon.africa.dtos.requests.UserRegistrationRequest;

public class Mapper {
    public User map(UserRegistrationRequest userRegistrationRequest, User user){
        user.setFirstName(userRegistrationRequest.getFirstName());
        user.setLastName(userRegistrationRequest.getLastName());
        user.setEmail(userRegistrationRequest.getEmail());
        user.setPassword(userRegistrationRequest.getPassword());
        user.setIndustry(userRegistrationRequest.getIndustry());
        user.setRole(userRegistrationRequest.getRole());
        return user;
    }
}
