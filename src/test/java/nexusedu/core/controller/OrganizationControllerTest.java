package kz.nexusedu.core.controller;

import kz.nexusedu.core.domain.Organization;
import kz.nexusedu.core.repository.OrganizationRepository;
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

@WebMvcTest(OrganizationController.class)
class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrganizationRepository organizationRepository;

    @Test
    void shouldReturnAllOrganizations() throws Exception {
        Organization org = new Organization();
        org.setId(UUID.randomUUID());
        org.setName("Test School");
        org.setType("SCHOOL");

        Mockito.when(organizationRepository.findAll()).thenReturn(List.of(org));

        mockMvc.perform(get("/api/organizations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test School"))
                .andExpect(jsonPath("$[0].type").value("SCHOOL"));
    }

    @Test
    void shouldCreateOrganization() throws Exception {
        Organization org = new Organization();
        org.setId(UUID.randomUUID());
        org.setName("New Academy");
        org.setType("ACADEMY");

        Mockito.when(organizationRepository.save(Mockito.any(Organization.class))).thenReturn(org);

        mockMvc.perform(post("/api/organizations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"New Academy\", \"type\": \"ACADEMY\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Academy"));
    }
}