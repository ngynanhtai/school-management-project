package com.project.security.jwt;

import com.project.constant.Constant;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Log4j2
@Getter
@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.access-expiration-ms}")
    private int jwtAccessExpirationMs;

    @Value("${jwt.refresh-expiration-ms}")
    private int jwtRefreshExpirationMs;

    public String generateAccessToken(String userName, String role) {
        return Jwts.builder()
                .setSubject((userName))
                .claim("group", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (Constant.STUDENT_ROLE.equalsIgnoreCase(role) ? jwtAccessExpirationMs : jwtAccessExpirationMs * 3L)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateRefreshToken(String userName, String role) {
        return Jwts.builder()
                .setSubject((userName))
                .claim("group", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (Constant.STUDENT_ROLE.equalsIgnoreCase(role) ? jwtRefreshExpirationMs : jwtRefreshExpirationMs * 3L)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    public Claims getClaims(String authToken) {
        if (StringUtils.isEmpty(authToken)) return null;

        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            if ( null == claimsJws || null == claimsJws.getBody()) return null;

            return claimsJws.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
