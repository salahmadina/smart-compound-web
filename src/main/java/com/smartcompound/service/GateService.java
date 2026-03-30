package com.smartcompound.service;

import com.smartcompound.dto.GateLogDto;
import com.smartcompound.entity.*;
import com.smartcompound.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GateService {

    private final QrTokenRepository qrTokenRepository;
    private final GateRepository gateRepository;
    private final GateLogRepository gateLogRepository;
    private final UserRepository userRepository;

    public String getOrCreateQrToken(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return qrTokenRepository.findByUser(user)
                .map(QrToken::getToken)
                .orElseGet(() -> {
                    String token = "QR-" + userId + "-" + UUID.randomUUID();
                    QrToken qt = QrToken.builder().user(user).token(token).build();
                    qrTokenRepository.save(qt);
                    return token;
                });
    }

    public void processGateAccess(Long userId, Long gateId, String token, String action) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        QrToken qrToken = qrTokenRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("No QR token found. Generate your QR code first."));

        if (!qrToken.getToken().equals(token))
            throw new IllegalArgumentException("Invalid QR token");

        Gate gate = gateRepository.findById(gateId)
                .orElseThrow(() -> new IllegalArgumentException("Gate not found"));

        GateLog log = GateLog.builder()
                .user(user)
                .gate(gate)
                .action(action.toUpperCase())
                .build();
        gateLogRepository.save(log);
    }

    public List<GateLogDto> getAllLogs() {
        return gateLogRepository.findAllByOrderByLoggedAtDesc()
                .stream().map(GateLogDto::from).toList();
    }

    public List<GateLogDto> getLogsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return gateLogRepository.findByUserOrderByLoggedAtDesc(user)
                .stream().map(GateLogDto::from).toList();
    }

    public List<com.smartcompound.entity.Gate> getAllGates() {
        return gateRepository.findAll();
    }
}
