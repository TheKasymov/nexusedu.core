package kz.nexusedu.core.controller;

import kz.nexusedu.core.domain.AttendanceLog;
import kz.nexusedu.core.repository.AttendanceLogRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceLogRepository repository;

    public AttendanceController(AttendanceLogRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public AttendanceLog logEvent(@RequestBody AttendanceLog attendanceLog) {
        if (attendanceLog.getEventTime() == null) {
            attendanceLog.setEventTime(LocalDateTime.now());
        }
        return repository.save(attendanceLog);
    }

    @GetMapping("/user/{userId}")
    public List<AttendanceLog> getByUser(@PathVariable UUID userId) {
        return repository.findByUserId(userId);
    }

    @GetMapping("/organization/{orgId}")
    public List<AttendanceLog> getByOrganization(@PathVariable UUID orgId) {
        return repository.findByOrganizationId(orgId);
    }
}