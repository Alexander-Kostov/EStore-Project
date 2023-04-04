package com.example.EStore.model.dto;

public class ReviewDTO {
    private String text;

    private String username;


    public String getText() {
        return text;
    }

    public ReviewDTO setText(String text) {
        this.text = text;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ReviewDTO setUsername(String username) {
        this.username = username;
        return this;
    }

}
