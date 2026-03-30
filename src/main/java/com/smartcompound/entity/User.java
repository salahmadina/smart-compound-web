package com.smartcompound.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "full_name", nullable = false, length = 200)
    private String fullName;

    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @Column(nullable = false, unique = true, length = 11)
    private String phone;

    @Column(name = "national_id", nullable = false, unique = true, length = 14)
    private String nationalId;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, length = 10)
    private String role; // RESIDENT or STAFF

    @Column(length = 200)
    private String dependants;

    @Column(name = "apartment_num")
    private Integer apartmentNum;

    @Column(name = "building_num")
    private Integer buildingNum;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
