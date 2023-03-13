package com.fantasypower.powerliftingfantasy.security.config;

import com.fantasypower.powerliftingfantasy.entity.AppUser;
import com.fantasypower.powerliftingfantasy.service.AppUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.CollationKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class JwtUtils {
    @Value("${jwt.secret.key}")
    private String jwtSigningKey;

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean hasClaim(String token, String claimName) {
        final Claims claims = extractAllClaims(token);
        return claims.get(claimName) != null;
    }

    private String createToken(Map<String, Object> claims, AppUser appUser) {
        return Jwts.builder().setClaims(claims).setSubject(appUser.getEmail())
                .claim("authorities", appUser.getAuthorities()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .signWith(SignatureAlgorithm.HS256, jwtSigningKey).compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSigningKey).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(AppUser appUser) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, appUser);
    }

    public String generateToken(AppUser appUser, Map<String, Object> claims) {
        return createToken(claims, appUser);
    }

    public boolean isTokenValid(String token, AppUser appUser) {
        final String email = extractEmail(token);
        return (email.equals(appUser.getEmail()) && !isTokenExpired(token));
    }
}
