package com.dashboard.exception;

import com.dashboard.model.AuthProvider;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoginWithProvider extends RuntimeException {
    public LoginWithProvider(String email, AuthProvider provider) {
        super(String.format("Unable to connect %s%nYou already have an account related to provider %s%nPlease connect with the following provider and then update your password in your profile section.", email, provider.name()));
    }
}
