package com.example.EStore.model.dto;

public class ChangeUserAuthorityDTO {
    private String newRole;

    public String getNewRole() {
        return newRole;
    }

    public ChangeUserAuthorityDTO setNewRole(String newRole) {
        this.newRole = newRole;
        return this;
    }
}
