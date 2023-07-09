package com.dashboard.dto.widget.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class WidgetDto {
    private final Long id;
    private final String name;
    private final String url;
    private final String serviceName;
    private final boolean active;
}
