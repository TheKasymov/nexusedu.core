package kz.nexusedu.core.service;

import kz.nexusedu.core.domain.AttendanceLog;
import kz.nexusedu.core.repository.AttendanceLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AttendanceService {

    private final AttendanceLogRepository repository;

    public AttendanceService(AttendanceLogRepository repository) {
        this.repository = repository;
    }

    public AttendanceLog logEvent(AttendanceLog attendanceLog) {
        if (attendanceLog.getEventTime() == null) {
            attendanceLog.setEventTime(LocalDateTime.now());
        }
        return repository.save(attendanceLog);
    }

    public List<AttendanceLog> getByUser(UUID userId) {
        return repository.findByUserId(userId);
    }

    public List<AttendanceLog> getByOrganization(UUID orgId) {
        return repository.findByOrganizationId(orgId);
    }
}