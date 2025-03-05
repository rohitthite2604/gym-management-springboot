package com.example.SpringStarter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SpringStarter.models.AttendanceLog;
import com.example.SpringStarter.repositories.AttendanceLogRepository;

@Service
public class AttendanceLogService {

    @Autowired
    private AttendanceLogRepository attendanceLogRepository;

    public void save(AttendanceLog log) {
        attendanceLogRepository.save(log);
    }
    public List<AttendanceLog> findByMembershipId(Long membershipId) {
        return attendanceLogRepository.findByMembershipId(membershipId);
    }
    public List<AttendanceLog> getAllLogs() {
        return attendanceLogRepository.findAll();
    }
}

