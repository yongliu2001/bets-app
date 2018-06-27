package com.yong.controller;

import com.yong.domain.User;
import com.yong.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;


/**
 * Created by yongliu on 27/6/18.
 */
public class UserControllerTest {


    @Mock
    private UserService userService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    private UserController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new UserController(userService, bCryptPasswordEncoder);
    }

    @Test
    public void signUp() throws Exception {

        User user = new User("admin","password");

        User savedUser = new User("admin","token");
        savedUser.setId(Long.valueOf("123456"));

        //given
        given(userService.saveUser(user)).willReturn(savedUser);
        given(bCryptPasswordEncoder.encode((user.getPassword()))).willReturn("token");

        //when
        controller.signUp(user);

        //assert
        assertEquals("token", savedUser.getPassword());


    }

}