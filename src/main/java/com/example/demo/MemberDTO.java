package com.example.demo;

import com.example.demo.entities.Members;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MemberDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String role;
    // Add other fields as needed

    // Constructor, getters, and setters
    public MemberDTO(Members member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.role = member.getRole();
        // Initialize other fields as needed
    }

    // Getters and Setters
}
