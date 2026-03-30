package com.smartcompound.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bus_stops")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @Column(name = "stop_name", nullable = false, length = 100)
    private String stopName;
}
