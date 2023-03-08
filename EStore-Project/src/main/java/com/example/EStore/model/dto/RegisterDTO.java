package com.example.EStore.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterDTO {

    private Long id;

    @Size(min = 2, max = 20, message = "First Name should be between 2 and 20 symbols")
    @NotBlank
    private String firstName;
    @Size(min = 2, max = 20, message = "Last Name should be between 2 and 20 symbols")
    private String lastName;
    @NotBlank
    @Size(min = 2, max = 20, message = "Password should be at least 10 symbols long")
    private String password;

    @NotBlank
    @Size(min = 2, max = 20, message = "Password should be at least 10 symbols long")
    private String confirmPassword;


    @Email(message = "Please insert a valid email", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 2, max = 30, message = "Please insert a valid email address")
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
