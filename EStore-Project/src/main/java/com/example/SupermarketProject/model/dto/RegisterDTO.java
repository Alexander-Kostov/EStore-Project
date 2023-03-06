package com.example.SupermarketProject.model.dto;


public class RegisterDTO {

    private Long id;
    private String username;

    private String password;

    private String email;

    private String address;

    private int mobileNumber;

    public Long getId() {
        return id;
    }

    public RegisterDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public RegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public RegisterDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public RegisterDTO setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }
}
