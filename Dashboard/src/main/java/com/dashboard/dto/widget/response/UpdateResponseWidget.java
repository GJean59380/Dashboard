package com.dashboard.dto.widget.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class UpdateResponseWidget {
    @Email
    @NotBlank
    private String email;

    @Size(min = 5)
    @NotBlank
    private String password;
}
