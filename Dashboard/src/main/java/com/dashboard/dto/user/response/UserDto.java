package com.dashboard.dto.user.response;

import com.dashboard.dto.userWidget.UserWidgetDto;
import com.dashboard.model.AuthProvider;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
public class UserDto {
    private final Long id;
    private final String name;
    private final String email;
    private final String imageUrl;
    private final Boolean emailVerified;
    private final AuthProvider provider;
    private final Set<UserWidgetDto> widgets;
    private final Set<String> roles;
    private final String providerId;
}
