package com.dashboard.dto.widget.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Data
@RequiredArgsConstructor
public class UpdateWidget {
    @Size(min = 2)
    private String name;
    @Size(min = 2)
    private String url;
    @Positive
    Long serviceId;
    Boolean active;
}
