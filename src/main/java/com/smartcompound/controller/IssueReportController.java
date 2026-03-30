package com.smartcompound.controller;

import com.smartcompound.dto.ApiResponse;
import com.smartcompound.dto.IssueReportDto;
import com.smartcompound.dto.UserDto;
import com.smartcompound.service.IssueReportService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class IssueReportController {

    private final IssueReportService reportService;

    @PostMapping
    public ResponseEntity<ApiResponse<IssueReportDto>> create(
            @RequestBody Map<String, String> body,
            HttpServletRequest httpReq) {
        try {
            UserDto user = getSessionUser(httpReq);
            IssueReportDto dto = reportService.create(
                    user.getId(),
                    body.get("issueType"),
                    body.get("description")
            );
            return ResponseEntity.ok(ApiResponse.ok("Report submitted and assigned to staff", dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<IssueReportDto>>> myReports(HttpServletRequest httpReq) {
        UserDto user = getSessionUser(httpReq);
        return ResponseEntity.ok(ApiResponse.ok(reportService.getMyReports(user.getId())));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<IssueReportDto>>> assignedToMe(HttpServletRequest httpReq) {
        UserDto user = getSessionUser(httpReq);
        return ResponseEntity.ok(ApiResponse.ok(reportService.getAssignedToStaff(user.getId())));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<IssueReportDto>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok(reportService.getAll()));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<IssueReportDto>> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        try {
            return ResponseEntity.ok(ApiResponse.ok(reportService.updateStatus(id, body.get("status"))));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    private UserDto getSessionUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return (UserDto) session.getAttribute("user");
    }
}
