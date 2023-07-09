package com.dashboard.controller;

import com.dashboard.dto.auth.request.Login;
import com.dashboard.dto.auth.request.Register;
import com.dashboard.dto.auth.response.LoginResponse;
import com.dashboard.repository.UserRepository;
import com.dashboard.security.TokenProvider;
import com.dashboard.util.CookieUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class AuthorizationTest {
    public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
    public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
    private static final int cookieExpireSeconds = 3600;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private WebApplicationContext webappContext;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webappContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void loginFail() throws Exception {
        Login login = new Login();
        login.setEmail("admin@dashboard.com");
        login.setPassword("qsdfghjklm");
        mockMvc.perform(RequestSecurityFactory.securityFactory("/auth/login", login))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void login() throws Exception {
        Login login = new Login();
        login.setEmail("admin@dashboard.com");
        login.setPassword("azertyuiop");
        mockMvc.perform(RequestSecurityFactory.securityFactory("/auth/login", login))
                .andExpect(status().isOk());
    }

    @Test
    public void register() throws Exception {
        Register register = new Register();
        register.setEmail("test@dashboard.com");
        register.setPassword("azertyuiop");
        register.setName("test123456");
        mockMvc.perform(RequestSecurityFactory.securityFactory("/auth/register", register))
                .andExpect(status().isOk());
    }


    @Test
    public void registerFail() throws Exception {
        Register register = new Register();
        register.setEmail("admin@dashboard.com");
        register.setPassword("azertyuiop");
        register.setName("admin");
        mockMvc.perform(RequestSecurityFactory.securityFactory("/auth/register", register))
                .andExpect(status().isBadRequest());
    }




    @Test
    @WithMockUser(username = "Jean", password = "", roles = "USER")
    public void generateCookie() throws Exception {
        Authentication authorizationCookieRequest = Mockito.mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        if (authorizationCookieRequest == null) {
            CookieUtils.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
            CookieUtils.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
        }
        Cookie cookie = CookieUtils.addCookieTest(response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME, CookieUtils.serialize(authorizationCookieRequest), cookieExpireSeconds);
        request.setAttribute(REDIRECT_URI_PARAM_COOKIE_NAME, cookie.getValue());
        String token = cookie.getValue();
        System.out.println("token = " + token);
        mockMvc.perform(RequestSecurityFactory.securityFactory("/user/token", token)
                        .header(AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk());
    }


    public static class RequestSecurityFactory {
        public static MockHttpServletRequestBuilder securityFactory(String url, String token) {
            return MockMvcRequestBuilders.get(url)
                    .header(AUTHORIZATION, "Bearer " + token);
        }


        public static MockHttpServletRequestBuilder securityFactory(String url, Object body) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            String requestJson = ow.writeValueAsString(body);
            return MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(requestJson);
        }
    }

}