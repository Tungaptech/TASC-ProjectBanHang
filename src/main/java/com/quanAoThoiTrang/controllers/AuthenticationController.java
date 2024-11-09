package com.quanAoThoiTrang.controllers;

import com.quanAoThoiTrang.dto.LoginRequest;
import com.quanAoThoiTrang.dto.LoginResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        // Kiểm tra thông tin đăng nhập
        if ("admin".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword())) {
            // Tạo token
            String token = generateToken(loginRequest.getUsername());
            return new LoginResponse(token);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    private String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, "your-secret-key")
                .compact();
    }
}
