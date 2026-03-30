package com.smartcompound.repository;

import com.smartcompound.entity.IssueReport;
import com.smartcompound.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueReportRepository extends JpaRepository<IssueReport, Long> {
    List<IssueReport> findByResidentOrderByCreatedAtDesc(User resident);
    List<IssueReport> findByAssignedStaffOrderByCreatedAtDesc(User staff);
    List<IssueReport> findAllByOrderByCreatedAtDesc();
}
