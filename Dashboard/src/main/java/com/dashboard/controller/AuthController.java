package com.dashboard.controller;

import com.dashboard.dto.auth.request.Login;
import com.dashboard.dto.auth.request.Register;
import com.dashboard.dto.auth.response.LoginResponse;
import com.dashboard.dto.user.response.UserDto;
import com.dashboard.exception.AuthenticationFailed;
import com.dashboard.exception.EmailAlreadyTaken;
import com.dashboard.exception.LoginWithProvider;
import com.dashboard.exception.ResourceNotFoundException;
import com.dashboard.model.AuthProvider;
import com.dashboard.model.User;
import com.dashboard.repository.RolesRepository;
import com.dashboard.repository.UserRepository;
import com.dashboard.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final TokenProvider tokenProvider;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody Login login) {
        Optional<User> optional = userRepository.findByEmail(login.getEmail());
        if (optional.isEmpty()) {
            throw new AuthenticationFailed(login.getEmail());
        } else if (optional.get().getPassword() == null) {
            throw new LoginWithProvider(login.getEmail(), optional.get().getProvider());
        } else if (passwordEncoder.matches(login.getPassword(), optional.get().
                getPassword())) {
            return new LoginResponse(tokenProvider.createToken(optional.get()), optional.get());
        }
        throw new
                AuthenticationFailed(login.getEmail());
    }

    @PostMapping("/register")
    public UserDto register(@Valid @RequestBody Register register) {
        if (userRepository.findByEmail(register.getEmail()).isPresent()) {
            throw new EmailAlreadyTaken(register.getEmail());
        }
        User user = new User();
        user.setProvider(AuthProvider.local);
        user.setName(register.getName());
        user.setEmail(register.getEmail());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.setRoles(user.addRole(rolesRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ResourceNotFoundException("Role", "name", "ROLE_USER"))));
        return userRepository.save(user).getUserDto();
    }

}

