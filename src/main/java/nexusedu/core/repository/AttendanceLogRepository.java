package kz.nexusedu.core.repository;

import kz.nexusedu.core.domain.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, UUID> {
    List<AttendanceLog> findByUserId(UUID userId);
    List<AttendanceLog> findByOrganizationId(UUID organizationId);
}