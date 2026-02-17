package kz.nexusedu.core.controller;

import kz.nexusedu.core.domain.AttendanceLog;
import kz.nexusedu.core.repository.AttendanceLogRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AttendanceController.class)
class AttendanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AttendanceLogRepository attendanceLogRepository;

    @Test
    void shouldReturnAttendanceByUser() throws Exception {
        UUID userId = UUID.randomUUID();
        AttendanceLog log = new AttendanceLog();
        log.setId(UUID.randomUUID());
        log.setUserId(userId);
        log.setDirection("IN");
        log.setAuthMethod("FACE_ID");

        Mockito.when(attendanceLogRepository.findByUserId(userId)).thenReturn(List.of(log));

        mockMvc.perform(get("/api/attendance/user/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].direction").value("IN"))
                .andExpect(jsonPath("$[0].authMethod").value("FACE_ID"));
    }

    @Test
    void shouldLogAttendance() throws Exception {
        UUID userId = UUID.randomUUID();
        UUID orgId = UUID.randomUUID();
        AttendanceLog log = new AttendanceLog();
        log.setId(UUID.randomUUID());
        log.setUserId(userId);
        log.setOrganizationId(orgId);
        log.setDirection("OUT");
        log.setEventTime(LocalDateTime.now());

        Mockito.when(attendanceLogRepository.save(Mockito.any(AttendanceLog.class))).thenReturn(log);

        mockMvc.perform(post("/api/attendance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": \"" + userId + "\", \"organizationId\": \"" + orgId + "\", \"direction\": \"OUT\", \"authMethod\": \"RFID\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.direction").value("OUT"));
    }
}