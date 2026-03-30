package com.smartcompound.dto;

import lombok.Data;

@Data
public class BookingRequest {
    private Long tripId;
    private int seats;
}
