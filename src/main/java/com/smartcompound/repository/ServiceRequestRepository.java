package com.smartcompound.repository;

import com.smartcompound.entity.ServiceRequest;
import com.smartcompound.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findByResidentOrderByCreatedAtDesc(User resident);
    List<ServiceRequest> findAllByOrderByCreatedAtDesc();
}
