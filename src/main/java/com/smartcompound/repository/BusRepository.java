package com.smartcompound.repository;

import com.smartcompound.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
    boolean existsByBusCode(String busCode);
}
