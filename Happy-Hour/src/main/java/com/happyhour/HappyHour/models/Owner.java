package com.happyhour.HappyHour.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Owner extends AbstractEntity {

    @NotBlank
    @Size(min=3, max = 50, message = "Username must be between 3 and 50 characters long")
    private String username;

    @NotBlank
    @Size(min = 8, max = 50, message = "Password must be between 3 and 50 characters long")
    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Owner(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}
