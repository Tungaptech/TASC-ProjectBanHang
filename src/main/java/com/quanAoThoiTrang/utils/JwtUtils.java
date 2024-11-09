package com.quanAoThoiTrang.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    // Nếu cần sử dụng khóa bí mật từ application.properties hoặc application.yml
    // @Value("${jwt.secret}")
    // private String secretKey;

    // Tạo khóa bí mật thích hợp cho HS512 từ phương thức Keys
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRATION_TIME = 86400000L; // 1 ngày

    // Tạo JWT token từ tên người dùng
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY) // Sử dụng khóa bí mật đã tạo từ Keys.secretKeyFor
                .compact();
    }

    // Xác minh JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY) // Sử dụng đúng khóa bí mật khi xác minh
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Lấy thông tin người dùng từ token (tên người dùng trong phần subject)
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // Sử dụng đúng khóa bí mật khi phân tích token
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Nếu bạn cần hàm này để sinh token từ email, bạn có thể sửa lại như sau:
    public String generateJwtToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }
}
