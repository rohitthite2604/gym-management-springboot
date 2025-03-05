package com.example.SpringStarter.models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class GymMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name Missing")
    private String name;

    @Email(message = "Invalid Email")
    @NotEmpty(message = "Email Missing")
    private String email;

    private String gender;

    private String duration;

    private int amount;

    private int remaining;

    private int age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL)
    private List<AttendanceLog> attendanceLogs;

    // Add more fields as needed (e.g., membershipType, startDate, etc.)

    // Getters and setters
}
