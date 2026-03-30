package com.smartcompound.dto;

import com.smartcompound.entity.GateLog;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GateLogDto {
    private Long id;
    private String username;
    private String fullName;
    private String gateLocation;
    private int gateNumber;
    private String action;
    private LocalDateTime loggedAt;

    public static GateLogDto from(GateLog log) {
        GateLogDto dto = new GateLogDto();
        dto.id = log.getId();
        dto.username = log.getUser().getUsername();
        dto.fullName = log.getUser().getFullName();
        dto.gateLocation = log.getGate().getLocation();
        dto.gateNumber = log.getGate().getGateNumber();
        dto.action = log.getAction();
        dto.loggedAt = log.getLoggedAt();
        return dto;
    }
}
