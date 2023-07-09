package com.dashboard.dto.about.response;

import com.dashboard.dto.param.request.ParamDto;
import lombok.Data;

import java.util.Set;

@Data
public class WidgetDtoAbout {
    private final String name;
    private final String description;
    private final Set<ParamDto> params;
}
