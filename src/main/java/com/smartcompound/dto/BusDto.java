package com.smartcompound.dto;

import com.smartcompound.entity.Bus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BusDto {
    private Long id;
    private String busCode;
    private int capacity;
    private BigDecimal pricePerKm;
    private BigDecimal distanceKm;
    private List<String> stops;
    private List<BusTripDto> trips;

    public static BusDto from(Bus bus) {
        BusDto dto = new BusDto();
        dto.id = bus.getId();
        dto.busCode = bus.getBusCode();
        dto.capacity = bus.getCapacity();
        dto.pricePerKm = bus.getPricePerKm();
        dto.distanceKm = bus.getDistanceKm();
        if (bus.getStops() != null)
            dto.stops = bus.getStops().stream().map(s -> s.getStopName()).toList();
        if (bus.getTrips() != null)
            dto.trips = bus.getTrips().stream().map(BusTripDto::from).toList();
        return dto;
    }
}
