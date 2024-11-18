package me.h_yang.my_homepage.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collection;

/**
 * Custom filter for handling login requests.
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationProvider authenticationProvider;
    private final JwtUtilProvider jwtUtilProvider;



    public LoginFilter(AuthenticationProvider authenticationProvider, JwtUtilProvider jwtUtilProvider) {
        this.authenticationProvider = authenticationProvider;
        this.jwtUtilProvider = jwtUtilProvider;
    }

    /**
     * Attempt to authenticate the user using the email and password provided in the request.
     *
     * @param request The HTTP request object.
     * @param response The HTTP response object.
     * @return The authentication object.
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // Extract email and password from the request
        String email = obtainUsername(request);
        String password = obtainPassword(request);


        // Create a new UsernamePasswordAuthenticationToken with the email and password
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password, null);

        // Return the authentication request
        return authenticationProvider.authenticate(authToken);
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request,
                                         HttpServletResponse response,
                                         FilterChain chain,
                                         Authentication authentication) {


        


    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request,
                                           HttpServletResponse response,
                                           AuthenticationException failed) {

    }

}
