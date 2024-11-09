package com.quanAoThoiTrang.controllers;

import com.quanAoThoiTrang.dto.LoginRequest;
import com.quanAoThoiTrang.dto.LoginResponse;
import com.quanAoThoiTrang.services.CustomerServiceImpl;
import com.quanAoThoiTrang.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final CustomerServiceImpl customerService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, CustomerServiceImpl customerService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String jwt = jwtUtils.generateJwtToken(loginRequest.getEmail());
        return ResponseEntity.ok(new LoginResponse(jwt));


    }
}

