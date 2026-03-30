package com.smartcompound.dto;

import com.smartcompound.entity.ServiceRequest;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServiceRequestDto {
    private Long id;
    private Long residentId;
    private String residentName;
    private String serviceType;
    private String details;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ServiceRequestDto from(ServiceRequest sr) {
        ServiceRequestDto dto = new ServiceRequestDto();
        dto.id = sr.getId();
        dto.residentId = sr.getResident().getId();
        dto.residentName = sr.getResident().getFullName();
        dto.serviceType = sr.getServiceType();
        dto.details = sr.getDetails();
        dto.status = sr.getStatus();
        dto.createdAt = sr.getCreatedAt();
        dto.updatedAt = sr.getUpdatedAt();
        return dto;
    }
}
