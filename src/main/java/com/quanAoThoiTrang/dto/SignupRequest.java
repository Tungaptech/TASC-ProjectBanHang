package com.quanAoThoiTrang.dto;

public class SignupRequest {

    private String email;
    private String password;
    private String name;

    // Constructor mặc định (không có tham số)
    public SignupRequest() {
    }

    // Constructor có tham số
    public SignupRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Getters và Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
