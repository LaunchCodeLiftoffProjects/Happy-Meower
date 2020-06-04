package com.happyhour.HappyHour.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner extends AbstractEntity {

    @NotBlank
    private String username;

    @NotBlank
    private String pwHash;

    @OneToMany
    @JoinColumn(name="owner_id")
    private final List<HappyHour> happyHours = new ArrayList<>();

    public Owner(){}

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Owner(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getPwHash() {
        return pwHash;
    }

    public String getUsername() {
        return username;
    }

    public List<HappyHour> getHappyHours() {
        return happyHours;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}
