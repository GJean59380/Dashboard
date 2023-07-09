package com.dashboard.dto.service.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ServiceDto {
    private final Long id;
    private final String name;
    private final String baseUrl;
    private final Boolean active;
    private final List<String> widgets;
}
