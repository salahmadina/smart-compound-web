package com.smartcompound.service;

import com.smartcompound.dto.BookingRequest;
import com.smartcompound.dto.BusBookingDto;
import com.smartcompound.dto.BusDto;
import com.smartcompound.dto.BusTripDto;
import com.smartcompound.entity.*;
import com.smartcompound.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;
    private final BusTripRepository busTripRepository;
    private final BusBookingRepository busBookingRepository;
    private final UserRepository userRepository;

    public List<BusDto> getAllBuses() {
        return busRepository.findAll().stream().map(BusDto::from).toList();
    }

    public List<BusTripDto> searchByDestination(String destination) {
        return busTripRepository.searchByDestination(destination)
                .stream().map(BusTripDto::from).toList();
    }

    @Transactional
    public BusBookingDto bookTrip(Long userId, BookingRequest req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        BusTrip trip = busTripRepository.findById(req.getTripId())
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        if (req.getSeats() < 1)
            throw new IllegalArgumentException("Must book at least 1 seat");
        if (trip.getAvailableSeats() < req.getSeats())
            throw new IllegalArgumentException("Not enough available seats. Available: " + trip.getAvailableSeats());

        trip.setAvailableSeats(trip.getAvailableSeats() - req.getSeats());
        busTripRepository.save(trip);

        BigDecimal farePerSeat = trip.getBus().getDistanceKm().multiply(trip.getBus().getPricePerKm());
        BigDecimal totalFare = farePerSeat.multiply(BigDecimal.valueOf(req.getSeats()));

        BusBooking booking = BusBooking.builder()
                .user(user)
                .trip(trip)
                .seatsBooked(req.getSeats())
                .totalFare(totalFare)
                .build();

        busBookingRepository.save(booking);
        return BusBookingDto.from(booking);
    }

    public List<BusBookingDto> getMyBookings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return busBookingRepository.findByUserOrderByBookedAtDesc(user)
                .stream().map(BusBookingDto::from).toList();
    }
}
