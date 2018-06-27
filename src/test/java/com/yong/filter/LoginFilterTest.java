package com.yong.filter;

import com.yong.controller.UserController;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/**
 * Created by yongliu on 27/6/18.
 */
public class LoginFilterTest {

    @Mock
    private AuthenticationManager authenticationManager;

    private MockHttpServletRequest request = new MockHttpServletRequest();

    private MockHttpServletResponse response = new MockHttpServletResponse();

    private LoginFilter loginFilter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        loginFilter = new LoginFilter(authenticationManager);
    }

    @Test
    public void attemptAuthentication() throws Exception {

        String json = "{\"username\": \"admin\", \"password\": \"password\"}";
        request.setContent(json.getBytes());

        Authentication expectedAuth = new TestingAuthenticationToken(null, null);

        given(authenticationManager.authenticate(any())).willReturn(expectedAuth);

        Authentication auth = loginFilter.attemptAuthentication(request, response);

        Assert.assertEquals("username", loginFilter.getUsernameParameter());
        Assert.assertEquals("password", loginFilter.getPasswordParameter());
        Assert.assertEquals(expectedAuth, auth);
    }

    @Test
    public void successfulAuthentication() throws Exception {

        org.springframework.security.core.userdetails.User principalUser = new User("admin", "password", new ArrayList<>());

        Authentication auth = new TestingAuthenticationToken(principalUser, null);

        loginFilter.successfulAuthentication(request, response, new MockFilterChain(), auth);


        Assert.assertNotNull(response.getHeader("Authorization"));
        Assert.assertTrue(response.getHeader("Authorization").contains("Bearer"));
    }

}
