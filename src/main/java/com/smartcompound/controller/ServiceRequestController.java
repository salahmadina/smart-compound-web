package com.smartcompound.controller;

import com.smartcompound.dto.ApiResponse;
import com.smartcompound.dto.ServiceRequestDto;
import com.smartcompound.dto.UserDto;
import com.smartcompound.service.ServiceRequestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class ServiceRequestController {

    private final ServiceRequestService requestService;

    @PostMapping
    public ResponseEntity<ApiResponse<ServiceRequestDto>> create(
            @RequestBody Map<String, String> body,
            HttpServletRequest httpReq) {
        try {
            UserDto user = getSessionUser(httpReq);
            ServiceRequestDto dto = requestService.create(
                    user.getId(),
                    body.get("serviceType"),
                    body.get("details")
            );
            return ResponseEntity.ok(ApiResponse.ok("Request submitted successfully", dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<ServiceRequestDto>>> myRequests(HttpServletRequest httpReq) {
        UserDto user = getSessionUser(httpReq);
        return ResponseEntity.ok(ApiResponse.ok(requestService.getMyRequests(user.getId())));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ServiceRequestDto>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok(requestService.getAll()));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<ServiceRequestDto>> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        try {
            return ResponseEntity.ok(ApiResponse.ok(requestService.updateStatus(id, body.get("status"))));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    private UserDto getSessionUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return (UserDto) session.getAttribute("user");
    }
}
