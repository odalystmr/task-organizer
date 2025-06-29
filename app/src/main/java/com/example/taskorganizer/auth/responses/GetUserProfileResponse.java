package com.example.taskorganizer.auth.responses;

import com.example.taskorganizer.user.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GetUserProfileResponse extends ResponseEntity {
    public GetUserProfileResponse(User user) {
        super(user.toString(), HttpStatus.OK);
    }
}