package com.portafolio.shop.shop_api.Security.JWT;

import com.portafolio.shop.shop_api.Security.Entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    // Ya no usamos @Value, ahora usamos nuestra clase de propiedades
    private final JwtProperties jwtProperties;
    private Key signingKey; // Guardamos la clave para no regenerarla cada vez

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        // Obtenemos y decodificamos la clave una sola vez en el constructor
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtProperties.getSecret());
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole().name());
        
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(this.signingKey, SignatureAlgorithm.HS256) // Usamos la clave ya procesada
                .compact();
    }
}