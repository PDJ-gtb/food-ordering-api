package com.restaurant.order.api.service;

import com.restaurant.order.api.entity.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Service
public class JwtService {

    private static final String SECRET_KEY = "my-super-secret-key-for-jwt-token-1234567890";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    public String generateToken(AppUser user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignInKey())
                .compact();
    }


    public String extractUsername(String token){
        Claims claim = extractAllClaims(token);
        return claim.getSubject();

    }

    public boolean isTokenValid(String token, AppUser user){
        if (extractUsername(token).equals(user.getUsername()) && !isTokenExpired(token)){
            return true;
        }
        return false;
    }
    public Date extractExpiration(String token) {
        Claims claim = extractAllClaims(token);
        return claim.getExpiration();

    }
    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    private Claims extractAllClaims(String token) {
       JwtParserBuilder parser= Jwts.parser();

       parser = parser.verifyWith(getSignInKey());
       return parser.build().parseSignedClaims(token).getPayload();
    }
    private boolean isTokenExpired(String token){
        Date date = new Date(System.currentTimeMillis());

        if (extractExpiration(token).before(date)){
            return true;
        }
        return false;
    }
}
