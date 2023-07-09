package com.dashboard.dto.param.request;

import lombok.Data;

import java.util.Set;
@Data
public class SetParams {
    private final Set<ParamDto> params;

}
