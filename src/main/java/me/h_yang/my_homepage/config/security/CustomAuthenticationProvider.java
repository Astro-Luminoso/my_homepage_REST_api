package me.h_yang.my_homepage.config.security;

import me.h_yang.my_homepage.config.token.JwtAuthenticationToken;
import me.h_yang.my_homepage.dto.ClientDetailDTO;
import me.h_yang.my_homepage.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Custom authentication provider for handling login requests.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private ClientService clientService;
    private JwtUtilProvider jwtUtilProvider;

    @Autowired
    public CustomAuthenticationProvider (ClientService clientService, JwtUtilProvider jwtUtilProvider) {
        super();
        this.clientService = clientService;
        this.jwtUtilProvider = jwtUtilProvider;
    }


    /**
     * Attempt to authenticate the user using the email and password provided in the request.
     *
     * @param authentication The authentication object.
     * @return The authentication object.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = String.valueOf(authentication.getName()).toLowerCase();
        String password = String.valueOf(authentication.getCredentials());
//        System.out.println("I am from CustomAuthenticationProvider");
//        System.out.println(email);
//        System.out.println(password);
        ClientDetailDTO clientDTO = clientService.findClientByEmail(email);


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8); // strength is temporary

        if(!encoder.matches(password, clientDTO.getPassword())) {
            throw new BadCredentialsException("Account is Invalid");
        }


        return new JwtAuthenticationToken(email, jwtUtilProvider.generateToken(email), clientDTO.getAuthorities());
    }

    /**
     * Check if the authentication object is supported.
     *
     * @param authentication The authentication object.
     * @return True if the authentication object is supported, false otherwise.
     */
    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println(UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
