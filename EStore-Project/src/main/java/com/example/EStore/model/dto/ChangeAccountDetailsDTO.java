package com.example.EStore.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChangeAccountDetailsDTO {
    @Size(min = 2, max = 20, message = "First Name should be between 2 and 20 symbols!")
    @NotBlank
    private String newFirstName;
    @Size(min = 2, max = 20, message = "Last Name should be between 2 and 20 symbols!")
    @NotBlank
    private String newLastName;
    @Size(min = 10, message = "Telephone number must be 10 digits in length!")
    @NotBlank
    private String newMobileNumber;
    @Email(message = "Please insert a valid email!", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @NotBlank
    private String newEmail;
    @Size(min = 2, max = 30, message = "Please insert a valid address!")
    @NotBlank(message = "")
    private String newAddress;

    public String getNewFirstName() {
        return newFirstName;
    }

    public ChangeAccountDetailsDTO setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
        return this;
    }

    public String getNewLastName() {
        return newLastName;
    }

    public ChangeAccountDetailsDTO setNewLastName(String newLastName) {
        this.newLastName = newLastName;
        return this;
    }

    public String getNewMobileNumber() {
        return newMobileNumber;
    }

    public ChangeAccountDetailsDTO setNewMobileNumber(String newMobileNumber) {
        this.newMobileNumber = newMobileNumber;
        return this;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public ChangeAccountDetailsDTO setNewEmail(String newEmail) {
        this.newEmail = newEmail;
        return this;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public ChangeAccountDetailsDTO setNewAddress(String newAddress) {
        this.newAddress = newAddress;
        return this;
    }
}
