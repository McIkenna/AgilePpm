package com.Iikenna.ppm.security;

import com.Iikenna.ppm.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.Iikenna.ppm.security.SecurityConstants.EXPIRATION_TIME;
import static com.Iikenna.ppm.security.SecurityConstants.SECRET;


@Component
public class JwtTokenProvider {
    public String generateToken(Authentication authentication){
        User user = (User)authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime()+EXPIRATION_TIME);

        String userId = Long.toString(user.getId());

        Map<String,Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId())));
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getFullname());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
}
