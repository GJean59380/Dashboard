package com.dashboard.dto.user.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;
import java.util.LinkedHashSet;

@Data
@RequiredArgsConstructor
public class UserUpdate {
    @Size(min = 2)
    private final String name;
    private final Boolean emailVerified;
    @Size(max = 9)
    private final LinkedHashSet<Long> widgets=new LinkedHashSet<>();
}


