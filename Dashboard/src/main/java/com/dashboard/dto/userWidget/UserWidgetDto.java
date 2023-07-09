package com.dashboard.dto.userWidget;

import com.dashboard.dto.widget.response.WidgetDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserWidgetDto {

    private final WidgetDto widget;

    private final Integer rank;
}
