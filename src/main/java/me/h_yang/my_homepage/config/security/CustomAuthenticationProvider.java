package me.h_yang.my_homepage.config.security;

import me.h_yang.my_homepage.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private ClientService clientService;
    private JwtProvider jwtProvider;

    @Autowired
    public CustomAuthenticationProvider (ClientService clientService, JwtProvider jwtProvider) {
        super();
        this.clientService = clientService;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
