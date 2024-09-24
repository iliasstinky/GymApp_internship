package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Subscriptions {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String type;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Members members;

    //FOR the member id to show in  api requests
    public int getMemberId() {
        return members != null ? members.getId() : 0;
    }



}