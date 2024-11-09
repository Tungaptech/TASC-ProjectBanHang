package com.quanAoThoiTrang.controllers;

import com.quanAoThoiTrang.utils.JwtUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final JwtUtils jwtUtils;

    public UserController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Logic kiểm tra username và password
        // Sau khi xác thực, tạo và trả về JWT token
        return jwtUtils.generateToken(username);
    }
}
