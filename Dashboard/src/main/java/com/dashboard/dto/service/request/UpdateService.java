package com.dashboard.dto.service.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Size;


@Data
@RequiredArgsConstructor
public class UpdateService {

    @Size(min = 5)

    private String name;

    @Size(min = 5)
    @URL
    private String baseUrl;


    private Boolean active = true;

}
