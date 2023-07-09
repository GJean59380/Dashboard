package com.dashboard.controller;

import com.dashboard.dto.user.request.UserUpdate;
import com.dashboard.dto.user.response.UserDto;
import com.dashboard.exception.ResourceNotFoundException;
import com.dashboard.model.User;
import com.dashboard.repository.UserRepository;
import com.dashboard.security.CurrentUser;
import com.dashboard.security.UserPrincipal;
import com.dashboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;


    @GetMapping("/whoami")
    public UserDto getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        User response = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
        return response.getUserDto();
    }

    @GetMapping("/token")
    public String getUserToken(@RequestHeader("Authorization") String authorization) {
        return authorization;
    }

    @GetMapping
    public Collection<UserDto> getUsers(@RequestParam(value = "sortDirection") String sortDirection, @RequestParam(value = "sortBy") String sortBy) {
        try {
            List<User> response = userRepository.findAll(Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
            Collection<UserDto> users = new ArrayList<>();
            response.forEach(u -> users.add(u.getUserDto()));
            return users;

        } catch (Exception e) {
            List<User> response = userRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
            Collection<UserDto> users = new ArrayList<>();
            response.forEach(u -> users.add(u.getUserDto()));
            return users;
        }
    }

    @GetMapping("/{userId}")
    public UserDto getUsers(@PathVariable(value = "userId") Long userId) {
        User response = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return response.getUserDto();
    }

    @PatchMapping()
    public UserDto updateUser(@CurrentUser UserPrincipal user, @Valid @RequestBody UserUpdate userUpdate) {
        User response = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", user.getId()));
        return userRepository.save(userService.updateUser(userUpdate, response)).getUserDto();
    }


}
