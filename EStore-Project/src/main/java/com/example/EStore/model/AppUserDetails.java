package com.example.EStore.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AppUserDetails extends User {

    private String firstName;

    private String lastName;

    private String address;

    private String mobileNumber;

    public AppUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getFirstName() {
        return firstName;
    }

    public AppUserDetails setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AppUserDetails setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AppUserDetails setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public AppUserDetails setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }
}
