package com.dashboard.dto.param.request;

import com.dashboard.model.Param;
import lombok.Data;


@Data
public class ParamDto {
    private final String name;
    private final String type;

    public Param dtoToParams(){
        Param p = new Param();
        p.setType(type);
        p.setName(name);
        return p;
    }
}
