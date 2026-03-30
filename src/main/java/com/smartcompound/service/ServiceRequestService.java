package com.smartcompound.service;

import com.smartcompound.dto.ServiceRequestDto;
import com.smartcompound.entity.ServiceRequest;
import com.smartcompound.entity.User;
import com.smartcompound.repository.ServiceRequestRepository;
import com.smartcompound.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ServiceRequestService {

    private final ServiceRequestRepository requestRepository;
    private final UserRepository userRepository;

    public ServiceRequestDto create(Long residentId, String serviceType, String details) {
        User resident = userRepository.findById(residentId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        ServiceRequest sr = ServiceRequest.builder()
                .resident(resident)
                .serviceType(serviceType)
                .details(details)
                .status("PENDING")
                .build();

        requestRepository.save(sr);
        return ServiceRequestDto.from(sr);
    }

    public List<ServiceRequestDto> getMyRequests(Long residentId) {
        User resident = userRepository.findById(residentId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return requestRepository.findByResidentOrderByCreatedAtDesc(resident)
                .stream().map(ServiceRequestDto::from).toList();
    }

    public List<ServiceRequestDto> getAll() {
        return requestRepository.findAllByOrderByCreatedAtDesc()
                .stream().map(ServiceRequestDto::from).toList();
    }

    public ServiceRequestDto updateStatus(Long id, String status) {
        ServiceRequest sr = requestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        List<String> valid = List.of("PENDING", "IN_PROGRESS", "DONE");
        if (!valid.contains(status.toUpperCase()))
            throw new IllegalArgumentException("Invalid status");

        sr.setStatus(status.toUpperCase());
        requestRepository.save(sr);
        return ServiceRequestDto.from(sr);
    }
}
