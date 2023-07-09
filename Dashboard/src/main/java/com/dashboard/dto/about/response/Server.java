package com.dashboard.dto.about.response;

import lombok.Data;

import java.util.Set;

@Data
public class Server {
    private final Long currentTime;
    private final Set<Services> services;
}
