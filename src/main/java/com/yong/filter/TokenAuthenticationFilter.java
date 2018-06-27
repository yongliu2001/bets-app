package com.yong.filter;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Token authorization filter
 *
 * Created by yongliu on 26/6/18.
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String user = null;
        UsernamePasswordAuthenticationToken authenticationToken = null;
        String token = request.getHeader("Authorization");


        if (token == null || !token.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        } else {
            user = Jwts.parser().setSigningKey("JwtSecret")
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody().getSubject();
        }
         if (user != null) {
             authenticationToken = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }


}
