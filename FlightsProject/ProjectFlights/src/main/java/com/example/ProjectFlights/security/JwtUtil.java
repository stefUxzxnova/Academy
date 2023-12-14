package com.example.ProjectFlights.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private String SECRET_KEY = "alabalabanica";
    public String generateToken(String email){
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String GetUsername(String token){
        return getDecodedJWT(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        return getDecodedJWT(token).getExpiresAt().before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails details){
        var username = getDecodedJWT(token).getSubject();
        boolean isValid = username.equals(details.getUsername()) && !isTokenExpired(token);
        return isValid;
    }

    public DecodedJWT getDecodedJWT(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
        return verifier.verify(token);
    }
}
