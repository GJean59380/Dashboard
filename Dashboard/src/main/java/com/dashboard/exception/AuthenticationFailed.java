package com.dashboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationFailed extends RuntimeException {
    public AuthenticationFailed(String email) {
        super(String.format("Failed to authenticate with the following email %s", email));
    }
}
