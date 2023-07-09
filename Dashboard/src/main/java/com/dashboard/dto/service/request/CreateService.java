package com.dashboard.dto.service.request;


import com.dashboard.model.Service;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Data
public class CreateService {

    @NotNull
    @Size(min = 5)
    @NotBlank
    private String name;

    @Size(min = 5)
    @URL
    @NotNull
    @NotBlank
    private String baseUrl;

    public Service dtoToService() {
        Service service = new Service();
        service.setName(this.name);
        service.setBaseUrl(this.baseUrl);
        service.setActive(true);
        return service;
    }


}
