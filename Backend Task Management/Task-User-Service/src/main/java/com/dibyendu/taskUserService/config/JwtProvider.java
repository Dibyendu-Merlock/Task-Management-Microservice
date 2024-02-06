package com.dibyendu.taskUserService.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JwtProvider
{
    static SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());

    public static String generateToken(Authentication authentication)
    {
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        String roles = populateAuthorities(grantedAuthorities);

        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email", authentication.getName())
                .claim("authorities", roles)
                .signWith(secretKey)
                .compact();

        return jwt;
    }

    public static String getEmailFromJwt(String jwt)
    {
        jwt = jwt.substring(7);

        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
        String email = String.valueOf(claims.get("email"));

        return email;
    }


    private static String populateAuthorities(Collection<? extends GrantedAuthority> grantedAuthoritiesCollection)
    {
        Set<String> authorities = new HashSet<>();

        for(GrantedAuthority authority:grantedAuthoritiesCollection)
        {
            authorities.add(authority.getAuthority());
        }

        return String.join(",",authorities);
    }
}
