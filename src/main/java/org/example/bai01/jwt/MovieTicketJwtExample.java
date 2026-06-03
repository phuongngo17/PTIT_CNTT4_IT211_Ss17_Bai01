package org.example.bai01.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MovieTicketJwtExample {

    public static void main(String[] args) {

        // (1) Tạo Secret Key
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // (2) Tạo claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", 123L);
        claims.put("roles", "USER");

        // (3) Thời gian tạo và hết hạn
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 3600 * 1000);

        // (4) Tạo JWT
        String jwtToken = Jwts.builder()
                .setClaims(claims)
                .setSubject("user@movieticket.com")
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        System.out.println("Generated JWT:");
        System.out.println(jwtToken);

        try {

            // (5) Xác minh JWT bằng đúng key ban đầu
            Claims body = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();

            System.out.println("\nJWT hợp lệ và đã được xác minh");

            // (6) Lấy dữ liệu từ payload
            String subject = body.getSubject();

            Long userId = body.get("userId", Long.class);

            String roles = body.get("roles", String.class);

            // (7) In thông tin
            System.out.println("Subject: " + subject);

            System.out.println("User ID: " + userId);

            System.out.println("Roles: " + roles);

        } catch (Exception e) {

            System.err.println("Invalid JWT: " + e.getMessage());
        }
    }
}