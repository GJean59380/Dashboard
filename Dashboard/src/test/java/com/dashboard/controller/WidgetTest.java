package com.dashboard.controller;

import com.dashboard.dto.auth.request.Login;
import com.dashboard.repository.UserRepository;
import com.dashboard.security.TokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class WidgetTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getWidgets() throws Exception {
        mockMvc.perform(AuthorizationTest.RequestSecurityFactory.securityFactory("/widget", tokenProvider.createToken(userRepository.findById(1L).get())))
                .andExpect(status().isOk());
    }


    @Test
    public void getWidgetsFail() throws Exception {
        mockMvc.perform(AuthorizationTest.RequestSecurityFactory.securityFactory("/widget", ""))
                .andExpect(status().isUnauthorized());
    }
}
