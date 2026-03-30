package com.smartcompound.dto;

import com.smartcompound.entity.BusBooking;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BusBookingDto {
    private Long id;
    private String busCode;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private int seatsBooked;
    private BigDecimal totalFare;
    private LocalDateTime bookedAt;

    public static BusBookingDto from(BusBooking b) {
        BusBookingDto dto = new BusBookingDto();
        dto.id = b.getId();
        dto.busCode = b.getTrip().getBus().getBusCode();
        dto.destination = b.getTrip().getDestination();
        dto.departureTime = b.getTrip().getDepartureTime();
        dto.arrivalTime = b.getTrip().getArrivalTime();
        dto.seatsBooked = b.getSeatsBooked();
        dto.totalFare = b.getTotalFare();
        dto.bookedAt = b.getBookedAt();
        return dto;
    }
}
