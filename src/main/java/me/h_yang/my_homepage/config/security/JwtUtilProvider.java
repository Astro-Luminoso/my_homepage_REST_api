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

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire-time}")
    private long expireTime;

    private SecretKey getSecretKey() {

        return new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS512.key().build().getAlgorithm());
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .header().type("JWT")
                .and()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSecretKey())
                .compact();
    }

}
