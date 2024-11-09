package com.quanAoThoiTrang.services;

import com.quanAoThoiTrang.dto.SignupRequest;

public interface AuthService {
    boolean createCustomer(SignupRequest signupRequest);
}
