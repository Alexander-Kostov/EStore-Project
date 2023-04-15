package com.example.EStore.model.dto;

import jakarta.validation.constraints.*;

public class CheckoutDTO {
    @NotBlank(message = "First name cannot be empty!")
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 symbols!")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty!")
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 symbols!")
    private String lastName;
    @NotBlank(message = "")
    @Email(message = "Please insert a valid email!", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    @Size(min = 10)
    @NotBlank
    private String mobileNo;
    @Size(min = 5, max = 30, message = "Please insert a valid address!")
    private String address;
    @NotBlank(message = "Country cannot be empty!")
    private String country;
    @NotBlank(message = "City cannot be empty!")
    private String city;
    private String state;
    @NotNull(message = "You must insert zip code!")
    private int zipCode;
    @NotNull(message = "You must select payment method!")
    private String paymentMethod;

    public String getFirstName() {
        return firstName;
    }

    public CheckoutDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CheckoutDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CheckoutDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public CheckoutDTO setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CheckoutDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public CheckoutDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public CheckoutDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public CheckoutDTO setState(String state) {
        this.state = state;
        return this;
    }

    public int getZipCode() {
        return zipCode;
    }

    public CheckoutDTO setZipCode(int zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public CheckoutDTO setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }
}
