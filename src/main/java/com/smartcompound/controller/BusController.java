package com.smartcompound.controller;

import com.smartcompound.dto.*;
import com.smartcompound.service.BusService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
@RequiredArgsConstructor
public class BusController {

    private final BusService busService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<BusDto>>> getAllBuses() {
        return ResponseEntity.ok(ApiResponse.ok(busService.getAllBuses()));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<BusTripDto>>> search(@RequestParam String destination) {
        return ResponseEntity.ok(ApiResponse.ok(busService.searchByDestination(destination)));
    }

    @PostMapping("/book")
    public ResponseEntity<ApiResponse<BusBookingDto>> book(@RequestBody BookingRequest req,
                                                            HttpServletRequest httpReq) {
        try {
            UserDto user = getSessionUser(httpReq);
            BusBookingDto booking = busService.bookTrip(user.getId(), req);
            return ResponseEntity.ok(ApiResponse.ok("Booking confirmed!", booking));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/bookings/my")
    public ResponseEntity<ApiResponse<List<BusBookingDto>>> myBookings(HttpServletRequest httpReq) {
        UserDto user = getSessionUser(httpReq);
        return ResponseEntity.ok(ApiResponse.ok(busService.getMyBookings(user.getId())));
    }

    private UserDto getSessionUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return (UserDto) session.getAttribute("user");
    }
}
