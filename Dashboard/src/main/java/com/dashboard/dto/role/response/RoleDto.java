package com.dashboard.dto.role.response;

import com.dashboard.dto.user.response.UserDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
public class RoleDto {
    private final Long id;
    private final String name;
    private final Set<UserDto> users;
}
