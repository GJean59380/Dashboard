package com.dashboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ServiceNameAlreadyExist extends RuntimeException {
    public ServiceNameAlreadyExist(String serviceName) {
        super(String.format("The service name: %s already been taken", serviceName));

    }
}
