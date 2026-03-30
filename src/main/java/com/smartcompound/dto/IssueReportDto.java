package com.smartcompound.dto;

import com.smartcompound.entity.IssueReport;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IssueReportDto {
    private Long id;
    private String reportUuid;
    private Long residentId;
    private String residentName;
    private String assignedStaffName;
    private String issueType;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static IssueReportDto from(IssueReport r) {
        IssueReportDto dto = new IssueReportDto();
        dto.id = r.getId();
        dto.reportUuid = r.getReportUuid();
        dto.residentId = r.getResident().getId();
        dto.residentName = r.getResident().getFullName();
        dto.assignedStaffName = r.getAssignedStaff() != null ? r.getAssignedStaff().getFullName() : null;
        dto.issueType = r.getIssueType();
        dto.description = r.getDescription();
        dto.status = r.getStatus();
        dto.createdAt = r.getCreatedAt();
        dto.updatedAt = r.getUpdatedAt();
        return dto;
    }
}
