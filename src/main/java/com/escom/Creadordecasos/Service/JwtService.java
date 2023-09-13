package com.escom.Creadordecasos.Service;

import com.escom.Creadordecasos.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import static org.springframework.cache.interceptor.SimpleKeyGenerator.generateKey;

@Service
public class JwtService {

    @Value( "${security.jwt.EXPIRATION_MINUTES}" )
    private Long EXPIRATION_MINUTES;

    @Value( "${security.jwt.SECRET_KEY}" )
    private String SECRET_KEY;

    public String generateToken( User user, Map<String, Object> extraClaims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES * (1000 * 60)));

        return Jwts.builder()
                .setClaims( extraClaims )
                .setSubject( user.getUsername() )
                .setIssuedAt( issuedAt )
                .setExpiration( expiration )
                .setHeaderParam( Header.TYPE, Header.JWT_TYPE )
                .signWith( generateKey(), SignatureAlgorithm.HS256 )
                .compact();
    }

    private Key generateKey() {
        byte[] secretAsBytes = Base64.getDecoder().decode( SECRET_KEY );
        return Keys.hmacShaKeyFor(secretAsBytes);
    }

    public String extractUsername(String jwt) {
        return extractAllClaims( jwt ).getSubject();
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey( generateKey() ).build()
                .parseClaimsJws( jwt ).getBody();
    }
}
