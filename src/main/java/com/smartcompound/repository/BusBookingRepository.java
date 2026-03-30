package com.smartcompound.repository;

import com.smartcompound.entity.BusBooking;
import com.smartcompound.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusBookingRepository extends JpaRepository<BusBooking, Long> {
    List<BusBooking> findByUserOrderByBookedAtDesc(User user);
}
