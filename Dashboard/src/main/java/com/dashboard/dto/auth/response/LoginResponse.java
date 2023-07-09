package com.dashboard.dto.auth.response;

import com.dashboard.dto.user.response.UserDto;
import com.dashboard.dto.userWidget.UserWidgetDto;
import com.dashboard.model.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class LoginResponse {
    private String token;
    private UserDto user;

    public LoginResponse(String token, User user) {
        this.token = token;
        Set<UserWidgetDto> widgets = new HashSet<>();
        Set<String> roles = new HashSet<>();
        user.getWidgetList().forEach(widget -> widgets.add(widget.userWidgetToDto()));
        user.getRoles().forEach(roles1 -> roles.add(roles1.getName()));

        this.user = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getImageUrl(), user.getEmailVerified(), user.getProvider(), widgets, roles, user.getProviderId());
    }
}
