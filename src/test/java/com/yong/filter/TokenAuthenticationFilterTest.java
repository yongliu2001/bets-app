package com.yong.filter;

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

import javax.servlet.FilterChain;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Created by yongliu on 27/6/18.
 */
public class TokenAuthenticationFilterTest {

    private static final String TOKEN = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUzMDA5OTY0Nn0.lP27mk05U-AGylWJKVNr33ZWp734skV1w0PwrgJsKwIrF17ZaTTQpApfJi8C2dAOxmRjGcoidoUi_rIseilpfg";

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private FilterChain chain;

    private MockHttpServletRequest request = new MockHttpServletRequest();

    private MockHttpServletResponse response = new MockHttpServletResponse();

    private TokenAuthenticationFilter filter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        filter = new TokenAuthenticationFilter(authenticationManager);
    }

    @Test
    public void doFilterInternal() throws Exception {

        String json = "{\"username\": \"admin\", \"password\": \"password\"}";
        request.setContent(json.getBytes());

        org.springframework.security.core.userdetails.User principalUser = new User("admin", "password", new ArrayList<>());

        Authentication auth = new TestingAuthenticationToken(principalUser, null);

        request.addHeader("Authorization", TOKEN);

        given(authenticationManager.authenticate(any())).willReturn(auth);

        filter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);

    }


}
