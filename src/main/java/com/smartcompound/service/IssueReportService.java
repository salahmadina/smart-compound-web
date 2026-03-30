package com.smartcompound.service;

import com.smartcompound.dto.IssueReportDto;
import com.smartcompound.entity.IssueReport;
import com.smartcompound.entity.User;
import com.smartcompound.repository.IssueReportRepository;
import com.smartcompound.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IssueReportService {

    private final IssueReportRepository reportRepository;
    private final UserRepository userRepository;

    public IssueReportDto create(Long residentId, String issueType, String description) {
        User resident = userRepository.findById(residentId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Round-robin auto-assign to staff
        List<User> staffList = userRepository.findByRoleOrderById("STAFF");
        User assignedStaff = null;
        if (!staffList.isEmpty()) {
            long reportCount = reportRepository.count();
            assignedStaff = staffList.get((int)(reportCount % staffList.size()));
        }

        IssueReport report = IssueReport.builder()
                .reportUuid(UUID.randomUUID().toString())
                .resident(resident)
                .assignedStaff(assignedStaff)
                .issueType(issueType)
                .description(description)
                .status("SENT")
                .build();

        reportRepository.save(report);
        return IssueReportDto.from(report);
    }

    public List<IssueReportDto> getMyReports(Long residentId) {
        User resident = userRepository.findById(residentId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return reportRepository.findByResidentOrderByCreatedAtDesc(resident)
                .stream().map(IssueReportDto::from).toList();
    }

    public List<IssueReportDto> getAssignedToStaff(Long staffId) {
        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return reportRepository.findByAssignedStaffOrderByCreatedAtDesc(staff)
                .stream().map(IssueReportDto::from).toList();
    }

    public List<IssueReportDto> getAll() {
        return reportRepository.findAllByOrderByCreatedAtDesc()
                .stream().map(IssueReportDto::from).toList();
    }

    public IssueReportDto updateStatus(Long id, String status) {
        IssueReport report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Report not found"));

        List<String> valid = List.of("SENT", "IN_PROGRESS", "SOLVED");
        if (!valid.contains(status.toUpperCase()))
            throw new IllegalArgumentException("Invalid status");

        report.setStatus(status.toUpperCase());
        reportRepository.save(report);
        return IssueReportDto.from(report);
    }
}
