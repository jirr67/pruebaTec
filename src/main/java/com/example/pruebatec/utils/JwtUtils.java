package com.example.pruebatec.utils;

import com.example.pruebatec.entity.User;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;


@Component
public class JwtUtils {


    public String generateJwts(User user) {
        return Jwts
                .builder()
                .claim("email", user.getEmail())
                .subject(user.getName())
                .id(UUID.randomUUID().toString())
                .issuedAt(new Date())
                .expiration(Date.from(Clock.systemUTC().instant().plus(5, ChronoUnit.MINUTES)))
                .compact();
    }
}
