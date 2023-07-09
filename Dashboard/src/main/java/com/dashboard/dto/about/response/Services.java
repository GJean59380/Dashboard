package com.dashboard.dto.about.response;


import lombok.Data;

import java.util.Set;

@Data
public class Services {
    private final String name;
    private final Set<WidgetDtoAbout> widgets;
}
