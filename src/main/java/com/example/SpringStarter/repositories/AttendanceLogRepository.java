package com.example.SpringStarter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SpringStarter.models.AttendanceLog;

public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Long> {
    List<AttendanceLog> findByMembershipId(Long membershipId);
}

