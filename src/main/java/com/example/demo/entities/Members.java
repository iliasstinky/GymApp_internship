package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString

@Getter
@Setter

@Entity
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;

    @Column(unique = true, nullable = false)
    private String username;
    int age;
    int phone;
    private String email;
    private String password;
    private String role = "USER";

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "members", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Subscriptions> subscription = new ArrayList<>();



    @ManyToMany(mappedBy = "member")
    @JsonIgnore
    private List<UserSession> userSessions = new ArrayList<>();

}

