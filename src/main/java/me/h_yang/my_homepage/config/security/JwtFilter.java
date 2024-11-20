package me.h_yang.my_homepage.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.h_yang.my_homepage.config.token.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Jwt Filter to check if the token is valid.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtilProvider jwtUtilProvider;

    @Autowired
    public JwtFilter(JwtUtilProvider jwtUtilProvider) {
        this.jwtUtilProvider = jwtUtilProvider;
    }


    /**
     * Check if the token is valid.
     *
     * @param request The HTTP request object.
     * @param response The HTTP response object.
     * @param filterChain The filter chain.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);

            return;
        }

        String token = authorization.split(" ")[1];

        if (jwtUtilProvider.isExpired(token)) {
           filterChain.doFilter(request, response);

           return;
        }


        Authentication jwtAuthentication = new JwtAuthenticationToken(jwtUtilProvider.getEmail(token), token, new ArrayList<GrantedAuthority>());

        SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);



    }
}
