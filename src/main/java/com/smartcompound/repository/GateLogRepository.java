package com.smartcompound.repository;

import com.smartcompound.entity.GateLog;
import com.smartcompound.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GateLogRepository extends JpaRepository<GateLog, Long> {
    List<GateLog> findAllByOrderByLoggedAtDesc();
    List<GateLog> findByUserOrderByLoggedAtDesc(User user);
}
