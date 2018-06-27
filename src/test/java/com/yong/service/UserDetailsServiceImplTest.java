package com.yong.service;

import com.yong.domain.User;
import com.yong.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Created by yongliu on 26/6/18.
 */
public class UserDetailsServiceImplTest {

    private UserDetailsServiceImpl userDetailsServiceimpl;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userDetailsServiceimpl = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    public void testLoadUserByUsername() throws Exception {

        //given
        User user = new User("admin","password");
        given(userRepository.findByUserName("admin")).willReturn(user);

        //when
        UserDetails admin = userDetailsServiceimpl.loadUserByUsername("admin");

        //assert and verify
        assertEquals(user.getPassword(), admin.getPassword());
        verify(userRepository).findByUserName("admin");

    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameFailed() throws Exception {

        //given
        given(userRepository.findByUserName("admin")).willReturn(null);

        //when
        userDetailsServiceimpl.loadUserByUsername("admin");

        //verify
        verify(userRepository).findByUserName("admin");

    }

}