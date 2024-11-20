package me.h_yang.my_homepage.config.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtilProvider {

    private final SecretKey secret;
    private final long expireTime;

    public JwtUtilProvider (@Value("${jwt.secret}") String secret, @Value("${jwt.expire-time}") long expireTime) {
        this.secret = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS512.key().build().getAlgorithm());
        this.expireTime = expireTime;
    }


    public String getEmail(String token) {

        return Jwts.parser().verifyWith(secret).build().parseSignedClaims(token).getPayload().get("email", String.class);
    }

    public boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secret).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

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
