package me.h_yang.my_homepage.config.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Provide utility of JSON Web Token
 */
@Component
public class JwtUtilProvider {

    private final SecretKey secret;
    private final long expireTime;

    /**
     * Constructor for the JwtUtilProvider.
     *
     * @param secret The secret key.
     * @param expireTime The expiration time.
     */
    public JwtUtilProvider (@Value("${jwt.secret}") String secret, @Value("${jwt.expire-time}") long expireTime) {
        this.secret = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS512.key().build().getAlgorithm());
        this.expireTime = expireTime;
    }


    /**
     * Get the email from the token.
     *
     * @param token The token.
     * @return The email.
     */
    public String getEmail(String token) {

        return Jwts.parser().verifyWith(secret).build().parseSignedClaims(token).getPayload().get("email", String.class);
    }

    /**
     * Check if the token is expired.
     *
     * @param token The token.
     * @return True if the token is expired, false otherwise.
     */
    public boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secret).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    /**
     * Generate a token.
     *
     * @param email The client's email.
     * @return The token.
     */
    public String generateToken(String email) {

        return Jwts.builder()
                .header().type("JWT")
                .and()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(this.secret)
                .compact();
    }

}
