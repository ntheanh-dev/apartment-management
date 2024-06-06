package com.ou.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ou.dto.request.AuthenticationRequest;
import com.ou.dto.response.AuthenticationResponse;
import com.ou.services.JwtService;
import com.ou.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:../webapp/WEB-INF/applicationContext.xml"})
public class ApiUserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ApiUserController apiUserController;

    @Mock
    private JwtService jwtService;
    @Mock
    private UserService userService;


    private AuthenticationRequest authenticationRequest;
    private AuthenticationResponse authenticationResponse;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(apiUserController).build();

        authenticationRequest = AuthenticationRequest.builder()
                .username("theanh")
                .password("password")
                .build();

        authenticationResponse = AuthenticationResponse.builder()
                .token("asdfasdfsdf")
                .build();
    }

    @Test
    public void login_success() throws Exception {
        //GIVEN
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(authenticationRequest);
        Mockito.when(jwtService.authenticated(ArgumentMatchers.any()))
                .thenReturn(authenticationResponse);

        //WHEN,THEN
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/token")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("result.token").value("asdfasdfsdf"))
        ;
    }
}
