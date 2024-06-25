package com.ou.services.impl;

import com.ou.dto.request.ChangePasswordRequest;
import com.ou.dto.request.UserCreationRequest;
import com.ou.dto.response.UserResponse;
import com.ou.exception.AppException;
import com.ou.mapper.UserMapper;
import com.ou.pojo.User;
import com.ou.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:../webapp/WEB-INF/applicationContext.xml"})
public class UserServiceImplTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserServiceImpl userServiceimpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private SecurityContext securityContext;
    @Mock
    private Authentication authentication;

    private User user;
    private UserResponse userResponse;
    private UserCreationRequest userCreationRequest;
    private ChangePasswordRequest changePasswordRequest;
    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userServiceimpl).build();

        userResponse = UserResponse.builder()
                .username("theanh")
                .build();

        userCreationRequest = UserCreationRequest.builder()
                .username("theanh")
                .password("12345678")
                .build();

        user = User.builder()
                .id(1)
                .username("theanh")
                .role("ROLE_USER")
                .active(true)
                .build();

        changePasswordRequest = ChangePasswordRequest.builder()
                .oldPassword("Password1")
                .newPassword("Password2")
                .build();
    }

    @Test
    public void addUser_validRequest_success() {
        //GIVEN
        Mockito.when(userRepository.userExistsByUsername(ArgumentMatchers.anyString())).thenReturn(false);
        Mockito.when(userRepository.addUser(user)).thenReturn(user);
        Mockito.when(userMapper.toUser(userCreationRequest)).thenReturn(user);
        Mockito.when(bCryptPasswordEncoder.encode(ArgumentMatchers.anyString())).thenReturn("");
        Mockito.when(userMapper.toUserResponse(user)).thenReturn(userResponse);
        //WHEN
        var response = userServiceimpl.addUser(userCreationRequest);
        //THEN
        Assert.assertEquals(response.getUsername(), userResponse.getUsername());
    }

    @Test
    public void addUser_userExisted_fail() {
        //GIVEN
        Mockito.when(userRepository.userExistsByUsername(ArgumentMatchers.anyString())).thenReturn(true);

        //WHEN
        var exception = Assert.assertThrows(AppException.class,() -> userServiceimpl.addUser(userCreationRequest));

        //THEN
        Assert.assertEquals(exception.getErrorCode().getCode(), 1002);
    }

    @Test
    public void changePassword_incorrectOldPasswordRequest_fail() {
        //GIVEN
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getName()).thenReturn(user.getUsername());
        Mockito.when(userRepository.getUserByUsername(user.getUsername())).thenReturn(user);
        Mockito.when(bCryptPasswordEncoder.matches(changePasswordRequest.getOldPassword(),user.getPassword()))
                .thenReturn(false);
        //WHEN
        var exception = Assert.assertThrows(AppException.class,
                () -> userServiceimpl.changePassword(changePasswordRequest)
        );
        //THEN
        Assert.assertEquals(exception.getErrorCode().getCode(), 1001);
    }
}
