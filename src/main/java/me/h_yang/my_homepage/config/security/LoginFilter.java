package me.h_yang.my_homepage.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.h_yang.my_homepage.dto.ClientDetailDTO;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

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

    /**
     * Handle a successful authentication.
     *
     * @param request The HTTP request object.
     * @param response The HTTP response object.
     * @param chain The filter chain.
     * @param authentication The authentication object.
     */
    @Override
    public void successfulAuthentication(HttpServletRequest request,
                                         HttpServletResponse response,
                                         FilterChain chain,
                                         Authentication authentication) {

        ClientDetailDTO clientDetailDTO = (ClientDetailDTO) authentication.getPrincipal();
        String userEmail = clientDetailDTO.getEmail();

       List<GrantedAuthority> authorities = clientDetailDTO.getAuthorities();

       String token = jwtUtilProvider.generateToken(userEmail);

       response.addHeader("Authorization", "Bearer " + token);
    }

    /**
     * Handle an unsuccessful authentication.
     *
     * @param request The HTTP request object.
     * @param response The HTTP response object.
     * @param failed The authentication exception.
     */
    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request,
                                           HttpServletResponse response,
                                           AuthenticationException failed) {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    }

}
