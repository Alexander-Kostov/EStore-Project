package com.example.EStore.model.views;

import java.time.LocalDate;

public class ReviewView {

    private Long id;

    private String text;

    private String username;

    private String email;

    private String dateOfCreation;


    public ReviewView(Long id, String text, String username, String email, String dateOfCreation) {
        this.id = id;
        this.text = text;
        this.username = username;
        this.email = email;
        this.dateOfCreation = dateOfCreation;
    }

    public Long getId() {
        return id;
    }

    public ReviewView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public ReviewView setText(String text) {
        this.text = text;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ReviewView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ReviewView setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public ReviewView setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
        return this;
    }
}
