package com.dashboard.dto.auth.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class Login {
    @Email
    @NotBlank
    private String email;

    @Size(min = 5)
    @NotBlank
    private String password;
}
