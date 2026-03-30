package com.smartcompound.repository;

import com.smartcompound.entity.Gate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GateRepository extends JpaRepository<Gate, Long> {
    boolean existsByGateNumber(int gateNumber);
    Optional<Gate> findByGateNumber(int gateNumber);
}
