package com.smartcompound.config;

import com.smartcompound.entity.*;
import com.smartcompound.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final GateRepository gateRepository;
    private final BusRepository busRepository;

    @Override
    public void run(String... args) {
        seedGates();
        seedBuses();
    }

    private void seedGates() {
        if (!gateRepository.existsByGateNumber(1)) {
            gateRepository.save(Gate.builder().gateNumber(1).location("Main Entrance").build());
        }
        if (!gateRepository.existsByGateNumber(2)) {
            gateRepository.save(Gate.builder().gateNumber(2).location("Back Entrance").build());
        }
    }

    private void seedBuses() {
        if (!busRepository.existsByBusCode("B1")) {
            Bus b1 = Bus.builder()
                    .busCode("B1")
                    .capacity(45)
                    .pricePerKm(new BigDecimal("2.50"))
                    .distanceKm(new BigDecimal("10.00"))
                    .build();
            b1.setStops(List.of(
                    BusStop.builder().bus(b1).stopName("Compound Gate").build(),
                    BusStop.builder().bus(b1).stopName("City Center").build(),
                    BusStop.builder().bus(b1).stopName("Downtown").build()
            ));
            b1.setTrips(List.of(
                    BusTrip.builder().bus(b1).destination("Downtown").departureTime("07:00").arrivalTime("07:45").availableSeats(45).build(),
                    BusTrip.builder().bus(b1).destination("Downtown").departureTime("09:00").arrivalTime("09:45").availableSeats(45).build(),
                    BusTrip.builder().bus(b1).destination("City Center").departureTime("11:00").arrivalTime("11:30").availableSeats(45).build(),
                    BusTrip.builder().bus(b1).destination("City Center").departureTime("14:00").arrivalTime("14:30").availableSeats(45).build()
            ));
            busRepository.save(b1);
        }

        if (!busRepository.existsByBusCode("B2")) {
            Bus b2 = Bus.builder()
                    .busCode("B2")
                    .capacity(45)
                    .pricePerKm(new BigDecimal("2.50"))
                    .distanceKm(new BigDecimal("15.00"))
                    .build();
            b2.setStops(List.of(
                    BusStop.builder().bus(b2).stopName("Compound Gate").build(),
                    BusStop.builder().bus(b2).stopName("University").build(),
                    BusStop.builder().bus(b2).stopName("Airport").build()
            ));
            b2.setTrips(List.of(
                    BusTrip.builder().bus(b2).destination("University").departureTime("07:30").arrivalTime("08:15").availableSeats(45).build(),
                    BusTrip.builder().bus(b2).destination("University").departureTime("12:00").arrivalTime("12:45").availableSeats(45).build(),
                    BusTrip.builder().bus(b2).destination("Airport").departureTime("06:00").arrivalTime("07:00").availableSeats(45).build(),
                    BusTrip.builder().bus(b2).destination("Airport").departureTime("16:00").arrivalTime("17:00").availableSeats(45).build()
            ));
            busRepository.save(b2);
        }

        if (!busRepository.existsByBusCode("B3")) {
            Bus b3 = Bus.builder()
                    .busCode("B3")
                    .capacity(45)
                    .pricePerKm(new BigDecimal("2.50"))
                    .distanceKm(new BigDecimal("8.00"))
                    .build();
            b3.setStops(List.of(
                    BusStop.builder().bus(b3).stopName("Compound Gate").build(),
                    BusStop.builder().bus(b3).stopName("Mall").build(),
                    BusStop.builder().bus(b3).stopName("Hospital").build()
            ));
            b3.setTrips(List.of(
                    BusTrip.builder().bus(b3).destination("Mall").departureTime("10:00").arrivalTime("10:30").availableSeats(45).build(),
                    BusTrip.builder().bus(b3).destination("Mall").departureTime("15:00").arrivalTime("15:30").availableSeats(45).build(),
                    BusTrip.builder().bus(b3).destination("Hospital").departureTime("08:00").arrivalTime("08:30").availableSeats(45).build(),
                    BusTrip.builder().bus(b3).destination("Hospital").departureTime("13:00").arrivalTime("13:30").availableSeats(45).build()
            ));
            busRepository.save(b3);
        }
    }
}
