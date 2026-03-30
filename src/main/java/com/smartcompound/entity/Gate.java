package com.smartcompound.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gate_number", nullable = false, unique = true)
    private int gateNumber;

    @Column(nullable = false, length = 100)
    private String location;
}
