package com.pedrojvdv.jdbc.model.enums;

public enum UserRole {
    ADMIN("Administrador"),
    USER("Usuário");

    private final String description;

    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
