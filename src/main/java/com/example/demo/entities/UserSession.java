package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserSession")
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;

    @Column(name = "location")
    private String location;

    @Column(name = "title")
    private String title;

    @Column(name = "time", columnDefinition = "TIME")
    private LocalTime time;

    @Column(name = "duration")
    private int duration;

    @Column(name = "coach")
    private String coach;

    @Column(name = "places_left")
    private int placesLeft;

    @ManyToMany
    @JsonIgnore
    @ToString.Exclude
    @JoinTable(
            name = "Member_UserSession",
            joinColumns = @JoinColumn(name = "userSession_id"),
            inverseJoinColumns = @JoinColumn(name ="member_id")

    )
    private List<Members>member = new ArrayList<>();

    public Boolean reserveSpot() {
        if (placesLeft > 0) {
            placesLeft--;
            return true;
        }
        return false;
    }
}
