package com.happyhour.HappyHour.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Owner extends AbstractEntity {

    @NotBlank
    private String username;

    @NotBlank
    private String pwHash;

    public Owner(){}

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
