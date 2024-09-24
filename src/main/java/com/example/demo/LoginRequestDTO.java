package com.example.demo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class LoginRequestDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId ;
    private String username;
    private String password;
    private String role;
    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}