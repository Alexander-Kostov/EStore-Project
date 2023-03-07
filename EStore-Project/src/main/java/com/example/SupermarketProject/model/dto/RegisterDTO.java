package com.example.SupermarketProject.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterDTO {

    private Long id;

    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @NotBlank
    @Size(min = 2)
    private String password;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 2)
    private String address;

    @Size(min = 10)
    @NotBlank
    private String mobileNumber;

    public Long getId() {
        return id;
    }

    public RegisterDTO setId(Long id) {
        this.id = id;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public RegisterDTO setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public RegisterDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public RegisterDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
