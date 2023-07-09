package com.dashboard.controller;

import com.dashboard.dto.user.request.UserUpdate;
import com.dashboard.dto.user.response.UserDto;
import com.dashboard.model.User;
import com.dashboard.repository.UserRepository;
import com.dashboard.security.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@AutoConfigureMockMvc
@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void whoami() throws Exception {
        User user = userRepository.findById(1L).get();
        String token = tokenProvider.createToken(user);
        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/user/whoami")
                                .header(AUTHORIZATION, "Bearer " + token)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    public void update() throws Exception {
        User user = userRepository.findById(1L).get();
        user.setName("toto");
        String token = tokenProvider.createToken(user);
        Set<WidgetTest> widgetTests = new HashSet<>();
        user.setWidgetList(new HashSet<>());
        user.setRoles(new HashSet<>());
        mockMvc
                .perform(
                        MockMvcRequestBuilders.patch("/user")
                                .content(objectMapper.writeValueAsBytes(user))
                                .header(AUTHORIZATION, "Bearer " + token)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("toto"));
    }

}
