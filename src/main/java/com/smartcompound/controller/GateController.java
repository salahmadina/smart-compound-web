package com.smartcompound.controller;

import com.smartcompound.dto.ApiResponse;
import com.smartcompound.dto.GateLogDto;
import com.smartcompound.dto.UserDto;
import com.smartcompound.entity.Gate;
import com.smartcompound.service.GateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gates")
@RequiredArgsConstructor
public class GateController {

    private final GateService gateService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Gate>>> getGates() {
        return ResponseEntity.ok(ApiResponse.ok(gateService.getAllGates()));
    }

    @GetMapping("/qr")
    public ResponseEntity<ApiResponse<String>> getQrToken(HttpServletRequest httpReq) {
        UserDto user = getSessionUser(httpReq);
        String token = gateService.getOrCreateQrToken(user.getId());
        return ResponseEntity.ok(ApiResponse.ok(token));
    }

    @PostMapping("/{gateId}/enter")
    public ResponseEntity<ApiResponse<Void>> enter(@PathVariable Long gateId,
                                                    @RequestBody Map<String, String> body,
                                                    HttpServletRequest httpReq) {
        try {
            UserDto user = getSessionUser(httpReq);
            gateService.processGateAccess(user.getId(), gateId, body.get("token"), "ENTRY");
            return ResponseEntity.ok(ApiResponse.ok("Entry logged successfully", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/{gateId}/exit")
    public ResponseEntity<ApiResponse<Void>> exit(@PathVariable Long gateId,
                                                   @RequestBody Map<String, String> body,
                                                   HttpServletRequest httpReq) {
        try {
            UserDto user = getSessionUser(httpReq);
            gateService.processGateAccess(user.getId(), gateId, body.get("token"), "EXIT");
            return ResponseEntity.ok(ApiResponse.ok("Exit logged successfully", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/logs")
    public ResponseEntity<ApiResponse<List<GateLogDto>>> getLogs(
            @RequestParam(required = false) Long userId) {
        if (userId != null) {
            return ResponseEntity.ok(ApiResponse.ok(gateService.getLogsByUser(userId)));
        }
        return ResponseEntity.ok(ApiResponse.ok(gateService.getAllLogs()));
    }

    private UserDto getSessionUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return (UserDto) session.getAttribute("user");
    }
}
