//package com.fantasypower.powerliftingfantasy.security.config;
//
//import com.fantasypower.powerliftingfantasy.entity.AppUser;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//public class JwtService {
//    @Value("${jwt.secret.key}")
//    public String SECRET_KEY;
//
//    private final Integer TWENTY_FOUR_HOURS_MILIS = 1000 * 60 * 24;
//
//    public String extractEmail(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public String extractUsername(String token) {
//        return null;
//    }
//
//    public String generateJwtTokenNoExtraClaims(AppUser appUser) {
//        return generateToken(new HashMap<>(), appUser);
//    }
//
//    public String generateToken(Map<String, Object> extractClaims, AppUser appUser) {
//        return Jwts.builder().setClaims(extractClaims).setSubject(appUser.getEmail())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + TWENTY_FOUR_HOURS_MILIS))
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
//    }
//
//    boolean isTokenValid(String token, AppUser appUser) {
//        final String userEmail = extractEmail(token);
//        return (userEmail.equals(appUser.getEmail()) && !isTokenExpired(token));
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
//    }
//
//    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}
