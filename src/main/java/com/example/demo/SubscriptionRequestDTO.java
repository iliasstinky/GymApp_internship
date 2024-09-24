package com.example.demo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@ToString
@Getter
@Setter
public class SubscriptionRequestDTO {
    private int memberId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String type;
}
