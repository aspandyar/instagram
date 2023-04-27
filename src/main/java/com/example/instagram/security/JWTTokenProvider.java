package com.example.instagram.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JWTTokenProvider {

    private final String TOKEN_EXCEPTION = "Произошла ошибка при проверке токена.";

    public String generateToken(UserPrincipal userPrincipal, String IP) {
        return JWT.create().withIssuer("").withAudience("")
                .withIssuedAt(new Date()).withSubject(userPrincipal.getUsername())
                .withClaim("IP", IP)
                .withExpiresAt(new Date(System.currentTimeMillis() + 180_000_000))
                .sign(HMAC512("secretKey".getBytes()));
    }

    public String getIP(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    public void isTokenValid(String token, HttpServletRequest request) {
        JWTVerifier jwtVerifier = getJWTVerifier();
        String IP = this.getIP(request);

        if (jwtVerifier.verify(token).getClaim("IP").asString().equals(IP)) {
            return;
        }

        throw new TokenExpiredException(this.TOKEN_EXCEPTION);
    }

    public String getSubject(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getSubject();
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier jwtVerifier;

        try {
            Algorithm algorithm = HMAC512("secretKey");
            jwtVerifier = JWT.require(algorithm).withIssuer("").build();
        }catch (JWTVerificationException e){
            throw new JWTVerificationException(this.TOKEN_EXCEPTION);
        }
        return jwtVerifier;
    }

}