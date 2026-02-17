package kz.nexusedu.core.controller;

import kz.nexusedu.core.domain.Membership;
import kz.nexusedu.core.repository.MembershipRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MembershipController.class)
class MembershipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MembershipRepository membershipRepository;

    @Test
    void shouldReturnMembershipsByUser() throws Exception {
        UUID userId = UUID.randomUUID();
        Membership membership = new Membership();
        membership.setId(UUID.randomUUID());
        membership.setUserId(userId);
        membership.setRole("TEACHER");

        Mockito.when(membershipRepository.findByUserId(userId)).thenReturn(List.of(membership));

        mockMvc.perform(get("/api/memberships/user/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].role").value("TEACHER"));
    }

    @Test
    void shouldCreateMembership() throws Exception {
        UUID userId = UUID.randomUUID();
        UUID orgId = UUID.randomUUID();
        Membership membership = new Membership();
        membership.setId(UUID.randomUUID());
        membership.setUserId(userId);
        membership.setOrganizationId(orgId);
        membership.setRole("STUDENT");

        Mockito.when(membershipRepository.save(Mockito.any(Membership.class))).thenReturn(membership);

        mockMvc.perform(post("/api/memberships")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": \"" + userId + "\", \"organizationId\": \"" + orgId + "\", \"role\": \"STUDENT\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("STUDENT"));
    }
}