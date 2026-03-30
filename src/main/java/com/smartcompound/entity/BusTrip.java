package com.smartcompound.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bus_trips")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @Column(nullable = false, length = 100)
    private String destination;

    @Column(name = "departure_time", nullable = false, length = 10)
    private String departureTime;

    @Column(name = "arrival_time", nullable = false, length = 10)
    private String arrivalTime;

    @Column(name = "available_seats", nullable = false)
    private int availableSeats;
}
