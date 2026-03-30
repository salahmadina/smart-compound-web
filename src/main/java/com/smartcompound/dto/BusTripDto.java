package com.smartcompound.dto;

import com.smartcompound.entity.BusTrip;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BusTripDto {
    private Long id;
    private Long busId;
    private String busCode;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private int availableSeats;
    private BigDecimal farePerSeat;

    public static BusTripDto from(BusTrip trip) {
        BusTripDto dto = new BusTripDto();
        dto.id = trip.getId();
        dto.busId = trip.getBus().getId();
        dto.busCode = trip.getBus().getBusCode();
        dto.destination = trip.getDestination();
        dto.departureTime = trip.getDepartureTime();
        dto.arrivalTime = trip.getArrivalTime();
        dto.availableSeats = trip.getAvailableSeats();
        dto.farePerSeat = trip.getBus().getDistanceKm().multiply(trip.getBus().getPricePerKm());
        return dto;
    }
}
