package com.yong.service;

import com.yong.domain.User;
import com.yong.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
/**
 * Created by yongliu on 26/6/18.
 */
public class UserServiceImplTest {

    private UserServiceImpl userServiceimpl;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userServiceimpl = new UserServiceImpl(userRepository);
    }

    @Test
    public void findUserByName() throws Exception {

        //given
        User user = new User("admin","password");
        given(userRepository.findByUserName("admin")).willReturn(user);

        //when
        User admin = userServiceimpl.findUserByName("admin");

        //verify
        assertEquals(user, admin);
        verify(userRepository).findByUserName("admin");
    }

    @Test
    public void saveUser() throws Exception {
        //given
        User user = new User("admin","password");
        User savedUser = new User("admin","password");
        savedUser.setId(Long.valueOf("123456"));
        given(userRepository.save(user)).willReturn(savedUser);


        //when
        User admin = userServiceimpl.saveUser(user);

        //assert and verify
        assertEquals(savedUser, admin);
        verify(userRepository).save(user);

    }

}