package com.smartcompound.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bus_bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private BusTrip trip;

    @Column(name = "seats_booked", nullable = false)
    private int seatsBooked;

    @Column(name = "total_fare", nullable = false, precision = 8, scale = 2)
    private BigDecimal totalFare;

    @Column(name = "booked_at")
    private LocalDateTime bookedAt;

    @PrePersist
    protected void onCreate() {
        bookedAt = LocalDateTime.now();
    }
}
