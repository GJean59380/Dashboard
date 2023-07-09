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
public class ServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenProvider tokenProvider;



    @Test
    public void getServices() throws Exception {
        mockMvc.perform(AuthorizationTest.RequestSecurityFactory.securityFactory("/service", tokenProvider.createToken(userRepository.findById(1L).get())))
                .andExpect(status().isOk());
    }


    @Test
    public void getServicesFail() throws Exception {
        mockMvc.perform(AuthorizationTest.RequestSecurityFactory.securityFactory("/service", ""))
                .andExpect(status().isUnauthorized());
    }
}
