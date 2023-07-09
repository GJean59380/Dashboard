package com.dashboard.dto.auth.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class Register {
    @NotBlank
    @Size(min = 2)
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 5)
    private String password;
}
