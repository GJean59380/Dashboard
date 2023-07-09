package com.dashboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyTaken extends RuntimeException {
    public EmailAlreadyTaken(String email) {
        super(String.format("The email %s already been taken", email));
    }
}
