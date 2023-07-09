package com.dashboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WidgetNameAlreadyExist extends RuntimeException {
    public WidgetNameAlreadyExist(String widgetName) {
        super(String.format("The widget name: %s already been taken", widgetName));

    }
}
